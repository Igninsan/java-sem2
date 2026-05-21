package movieapp.command.handlers;

import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;

/**
 * Очищает коллекцию.
 */
public class ClearCommand extends AbstractCommand {
    public ClearCommand() {
        super("clear", "clear : очистить коллекцию");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        context.repository().clear();
        context.output().println("Коллекция очищена.");
    }
}
