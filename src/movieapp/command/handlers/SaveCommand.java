package movieapp.command.handlers;

import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;

/**
 * Сохраняет коллекцию.
 */
public class SaveCommand extends AbstractCommand {
    public SaveCommand() {
        super("save", "save : сохранить коллекцию в файл");
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        context.repository().saveToStorage();
        context.output().println("Коллекция сохранена.");
    }
}
