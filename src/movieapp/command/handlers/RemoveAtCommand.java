package movieapp.command.handlers;

import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;

/**
 * Удаляет по индексу.
 */
public class RemoveAtCommand extends AbstractCommand {
    public RemoveAtCommand() {
        super("remove_at", "remove_at index : удалить элемент по индексу");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        Integer index = parseInt(arguments, context);
        if (index == null) {
            return;
        }
        boolean removed = context.repository().removeAt(index);
        context.output().println(removed ? "Элемент удалён." : "Некорректный индекс.");
    }

    private Integer parseInt(String arguments, CommandContext context) {
        if (arguments == null || arguments.isBlank()) {
            context.output().println("Ошибка: не указан индекс.");
            return null;
        }
        try {
            return Integer.parseInt(arguments.trim());
        } catch (NumberFormatException ex) {
            context.output().println("Ошибка: индекс должен быть целым числом.");
            return null;
        }
    }
}
