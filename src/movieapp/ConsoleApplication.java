package movieapp;

import movieapp.collection.MovieRepository;
import movieapp.command.CommandContext;
import movieapp.command.CommandRegistry;
import movieapp.command.handlers.*;
import movieapp.console.ConsoleInput;
import movieapp.console.ConsoleOutput;
import movieapp.input.ConsoleFieldReader;
import movieapp.script.ScriptExecutor;

import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Objects;

/**
 * Консольное приложение.
 */
public class ConsoleApplication {
    private final MovieRepository repository;
    private final ConsoleInput input;
    private final ConsoleOutput output;
    private final CommandRegistry registry = new CommandRegistry();
    private final ScriptExecutor scriptExecutor;
    private final ArrayDeque<String> history = new ArrayDeque<>();

    public ConsoleApplication(MovieRepository repository, ConsoleInput input, ConsoleOutput output) {
        this.repository = Objects.requireNonNull(repository, "repository не может быть null");
        this.input = Objects.requireNonNull(input, "input не может быть null");
        this.output = Objects.requireNonNull(output, "output не может быть null");
        this.scriptExecutor = new ScriptExecutor(registry);
        registerCommands();
    }

    private void registerCommands() {
        registry.register(new HelpCommand(registry));
        registry.register(new InfoCommand());
        registry.register(new ShowCommand());
        registry.register(new AddCommand());
        registry.register(new UpdateCommand());
        registry.register(new RemoveByIdCommand());
        registry.register(new ClearCommand());
        registry.register(new SaveCommand());
        registry.register(new ExecuteScriptCommand());
        registry.register(new ExitCommand());
        registry.register(new RemoveAtCommand());
        registry.register(new AddIfMaxCommand());
        registry.register(new HistoryCommand());
        registry.register(new MaxByGoldenPalmCountCommand());
        registry.register(new CountLessThanOperatorCommand());
        registry.register(new CountGreaterThanGenreCommand());
        registry.register(new AverageOscarCountCommand());
    }

    public void run() {
        try {
            repository.loadFromStorage();
        } catch (Exception ex) {
            output.println("Предупреждение: не удалось загрузить коллекцию из файла. Будет использована пустая коллекция.");
        }
        output.println("Приложение запущено. Для справки введите help.");
        CommandContext context = new CommandContext(
                repository,
                input,
                output,
                new ConsoleFieldReader(input, output),
                scriptExecutor,
                history,
                true,
                (Path) null,
                0
        );
        while (true) {
            try {
                String line = input.readLine();
                if (line == null) {
                    output.println("Ввод завершён. Программа остановлена.");
                    return;
                }
                scriptExecutor.executeLine(context, line);
            } catch (ExitSignal e) {
                output.println("Работа завершена.");
                return;
            } catch (Exception ex) {
                output.println("Критическая ошибка: " + ex.getMessage());
            }
        }
    }

    /**
     * Сигнал выхода.
     */
    public static class ExitSignal extends RuntimeException {
        public ExitSignal(String message) {
            super(message);
        }
    }
}
