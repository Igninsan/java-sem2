package movieapp.command.handlers;

import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;
import movieapp.model.MovieGenre;

/**
 * Считает элементы с жанром больше заданного.
 */
public class CountGreaterThanGenreCommand extends AbstractCommand {
    public CountGreaterThanGenreCommand() {
        super("count_greater_than_genre", "count_greater_than_genre genre : вывести количество элементов");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        if (arguments == null || arguments.isBlank()) {
            context.output().println("Ошибка: не указан жанр.");
            return;
        }
        try {
            MovieGenre genre = MovieGenre.valueOf(arguments.trim());
            long count = context.repository().countGreaterThanGenre(genre);
            context.output().println("Количество элементов: " + count);
        } catch (IllegalArgumentException ex) {
            context.output().println("Ошибка: неизвестный жанр.");
        }
    }
}
