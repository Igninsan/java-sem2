package movieapp.command.handlers;

import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;

/**
 * Показывает фильм с максимальным количеством пальм.
 */
public class MaxByGoldenPalmCountCommand extends AbstractCommand {
    public MaxByGoldenPalmCountCommand() {
        super("max_by_golden_palm_count", "max_by_golden_palm_count : вывести фильм с максимальным goldenPalmCount");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        var movie = context.repository().getMaxByGoldenPalmCount();
        if (movie == null) {
            context.output().println("Коллекция пуста.");
        } else {
            context.output().println(movie.toString());
        }
    }
}
