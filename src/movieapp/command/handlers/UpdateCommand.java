package movieapp.command.handlers;

import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;
import movieapp.input.MovieInputFactory;

import java.time.ZonedDateTime;

/**
 * Обновляет элемент по id.
 */
public class UpdateCommand extends AbstractCommand {
    public UpdateCommand() {
        super("update", "update id : обновить элемент коллекции по id");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        if (arguments == null || arguments.isBlank()) {
            context.output().println("Ошибка: не указан id.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(arguments.trim());
        } catch (NumberFormatException ex) {
            context.output().println("Ошибка: id должен быть целым числом.");
            return;
        }
        if (context.repository().findById(id).isEmpty()) {
            context.output().println("Элемент с id=" + id + " не найден.");
            return;
        }
        MovieInputFactory factory = new MovieInputFactory(context.fieldReader());
        var replacement = factory.createMovie(id, ZonedDateTime.now());
        var updated = context.repository().update(id, replacement);
        if (updated == null) {
            context.output().println("Не удалось обновить элемент.");
        } else {
            context.output().println("Элемент с id=" + id + " обновлён.");
        }
    }
}
