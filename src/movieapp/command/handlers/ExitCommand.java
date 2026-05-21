package movieapp.command.handlers;

import movieapp.ConsoleApplication;
import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;

/**
 * Завершает работу.
 */
public class ExitCommand extends AbstractCommand {
    public ExitCommand() {
        super("exit", "exit : завершить программу");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        throw new ConsoleApplication.ExitSignal("Выход из программы.");
    }
}
