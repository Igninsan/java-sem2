package movieapp.command;

/**
 * Базовая реализация команды.
 */
public abstract class AbstractCommand implements Command {
    private final String name;
    private final String description;

    protected AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }
}
