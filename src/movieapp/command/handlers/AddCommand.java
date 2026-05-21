package movieapp.command.handlers;

import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;
import movieapp.input.MovieInputFactory;

import java.time.ZonedDateTime;

/**
 * Добавляет элемент.
 */
public class AddCommand extends AbstractCommand {
    public AddCommand() {
        super("add", "add : добавить новый элемент в коллекцию");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        MovieInputFactory factory = new MovieInputFactory(context.fieldReader());
        int id = context.repository().nextId();
        var movie = factory.createMovie(id, ZonedDateTime.now());
        context.repository().add(movie);
        context.output().println("Фильм добавлен с id=" + movie.getId());
    }
}
