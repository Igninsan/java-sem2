package movieapp.command.handlers;

import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;
import movieapp.input.MovieInputFactory;
import movieapp.model.Movie;

import java.time.ZonedDateTime;

public class AddIfMaxCommand extends AbstractCommand {
    public AddIfMaxCommand() {
        super("add_if_max", "add_if_max : добавить элемент, если он больше максимального");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        MovieInputFactory factory = new MovieInputFactory(context.fieldReader());
        Movie candidate = factory.createMovie(context.repository().nextId(), ZonedDateTime.now());

        Movie max = null;
        for (Movie m : context.repository().findAll()) {
            if (max == null || m.compareTo(max) > 0) max = m;
        }

        if (max == null || candidate.compareTo(max) > 0) {
            context.repository().add(candidate);
            context.output().println("Элемент добавлен.");
        } else {
            context.output().println("Элемент не добавлен: он не превышает максимальный.");
        }
    }
}