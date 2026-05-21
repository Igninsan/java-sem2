package movieapp.command.handlers;

import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;

/**
 * Выводит все элементы.
 */
public class ShowCommand extends AbstractCommand {
    public ShowCommand() {
        super("show", "show : вывести все элементы коллекции");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        if (context.repository().size() == 0) {
            context.output().println("Коллекция пуста.");
            return;
        }
        context.repository().findAll().forEach(movie -> context.output().println(movie.toString()));
    }
}
