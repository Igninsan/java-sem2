package movieapp.command.handlers;

import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;

/**
 * Считает элементы с оператором меньше заданного.
 */
public class CountLessThanOperatorCommand extends AbstractCommand {
    public CountLessThanOperatorCommand() {
        super("count_less_than_operator", "count_less_than_operator operator : вывести количество элементов");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        if (arguments == null || arguments.isBlank()) {
            context.output().println("Ошибка: не указано значение operator.");
            return;
        }
        long count = context.repository().countLessThanOperator(arguments.trim());
        context.output().println("Количество элементов: " + count);
    }
}
