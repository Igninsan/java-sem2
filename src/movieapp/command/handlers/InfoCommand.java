package movieapp.command.handlers;

import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;

/**
 * Печатает информацию о коллекции.
 */
public class InfoCommand extends AbstractCommand {
    public InfoCommand() {
        super("info", "info : вывести информацию о коллекции");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        context.output().println(context.repository().info().toString());
    }
}
