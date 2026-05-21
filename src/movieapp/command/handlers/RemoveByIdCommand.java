package movieapp.command.handlers;

import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;

/**
 * Удаляет элемент по id.
 */
public class RemoveByIdCommand extends AbstractCommand {
    public RemoveByIdCommand() {
        super("remove_by_id", "remove_by_id id : удалить элемент по id");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        Integer id = parseInt(arguments, context);
        if (id == null) {
            return;
        }
        boolean removed = context.repository().removeById(id);
        context.output().println(removed ? "Элемент удалён." : "Элемент с id=" + id + " не найден.");
    }

    private Integer parseInt(String arguments, CommandContext context) {
        if (arguments == null || arguments.isBlank()) {
            context.output().println("Ошибка: не указан id.");
            return null;
        }
        try {
            return Integer.parseInt(arguments.trim());
        } catch (NumberFormatException ex) {
            context.output().println("Ошибка: id должен быть целым числом.");
            return null;
        }
    }
}
