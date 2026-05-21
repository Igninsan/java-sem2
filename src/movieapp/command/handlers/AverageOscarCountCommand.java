package movieapp.command.handlers;


import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;

public class AverageOscarCountCommand extends AbstractCommand {
    public AverageOscarCountCommand() {
        super("average_oscar_count", "Показывает среднее количество Оскаров в коллекции");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        if (arguments == null) {
            return;
        }
        long avg_count = context.repository().getAverageOscar();
        context.output().println("Среднее количество оскаров в фильмах: " + avg_count);
    }
}
