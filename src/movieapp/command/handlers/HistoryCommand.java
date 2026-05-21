package movieapp.command.handlers;

import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;

/**
 * Показывает историю.
 */
public class HistoryCommand extends AbstractCommand {
    public HistoryCommand() {
        super("history", "history : вывести последние 12 команд");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        if (context.history().isEmpty()) {
            context.output().println("История пуста.");
            return;
        }
        context.history().forEach(cmd -> context.output().println(cmd));
    }
}
