package movieapp.command.handlers;

import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;

import java.nio.file.Path;

/**
 * Выполняет скрипт.
 */
public class ExecuteScriptCommand extends AbstractCommand {
    public ExecuteScriptCommand() {
        super("execute_script", "execute_script file_name : выполнить скрипт из файла");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        if (arguments == null || arguments.isBlank()) {
            context.output().println("Ошибка: не указан файл скрипта.");
            return;
        }
        int depth = context.getDepth();
        Path base = context.currentScriptPath() == null ? Path.of("") : context.currentScriptPath().getParent();
        Path scriptPath = Path.of(arguments.trim());
        if (!scriptPath.isAbsolute() && base != null) {
            scriptPath = base.resolve(scriptPath);
        }
        context.scriptExecutor().execute(scriptPath, context, depth);
    }
}
