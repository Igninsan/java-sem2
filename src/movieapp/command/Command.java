package movieapp.command;

/**
 * Команда приложения.
 */
public interface Command {
    String name();

    String description();

    void execute(CommandContext context, String arguments);
}
