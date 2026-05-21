package movieapp.script;

import movieapp.command.CommandContext;
import movieapp.command.CommandParser;
import movieapp.command.CommandRegistry;
import movieapp.console.ConsoleInput;
import movieapp.console.ScriptConsoleInput;
import movieapp.input.ConsoleFieldReader;
import movieapp.ConsoleApplication;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

/**
 * Исполняет скрипты с ограничением глубины.
 */
public class ScriptExecutor {
    private final CommandRegistry registry;
    private final CommandParser parser = new CommandParser();
    private static final int MAX_DEPTH = 10;

    public ScriptExecutor(CommandRegistry registry) {
        this.registry = Objects.requireNonNull(registry);
    }

    public void execute(Path scriptPath, CommandContext parentContext) {
        execute(scriptPath, parentContext, 0);
    }

    public void execute(Path scriptPath, CommandContext parentContext, int depth) {
        if (depth >= MAX_DEPTH) {
            parentContext.output().println("Ошибка: превышена максимальная глубина вложенности скриптов (" + MAX_DEPTH + ").");
            return;
        }

        Path normalized = scriptPath.toAbsolutePath().normalize();
        File file = normalized.toFile();
        if (!file.exists() || !file.isFile()) {
            parentContext.output().println("Ошибка: файл скрипта не найден: " + normalized);
            return;
        }

        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (IOException ex) {
            parentContext.output().println("Ошибка: не удалось прочитать скрипт: " + normalized);
            return;
        }

        ConsoleInput scriptInput = new ScriptConsoleInput(lines);
        CommandContext childContext = new CommandContext(
                parentContext.repository(),
                scriptInput,
                parentContext.output(),
                new ConsoleFieldReader(scriptInput, parentContext.output()),
                this,
                parentContext.history(),
                false,
                normalized,
                depth + 1
        );
        runLoop(childContext, depth + 1);
    }

    private void runLoop(CommandContext context, int depth) {
        while (true) {
            String line = context.input().readLine();
            if (line == null) return;
            executeLine(context, line, depth);
        }
    }

    public void executeLine(CommandContext context, String line) {
        executeLine(context, line, 0);
    }

    private void executeLine(CommandContext context, String line, int scriptDepth) {
        if (line == null) return;
        CommandParser.ParsedCommand parsed = parser.parse(line);
        if (parsed.name().isBlank()) return;

        context.history().addLast(parsed.name());
        while (context.history().size() > 12) context.history().removeFirst();

        registry.find(parsed.name()).ifPresentOrElse(
                command -> {
                    try {
                        command.execute(context, parsed.arguments());
                    } catch (RuntimeException ex) {
                        if (ex instanceof ConsoleApplication.ExitSignal) throw ex;
                        context.output().println("Ошибка выполнения команды '" + parsed.name() + "': " + ex.getMessage());
                    } catch (Exception ex) {
                        context.output().println("Ошибка выполнения команды '" + parsed.name() + "': " + ex.getMessage());
                    }
                },
                () -> context.output().println("Неизвестная команда: " + parsed.name())
        );
    }
}