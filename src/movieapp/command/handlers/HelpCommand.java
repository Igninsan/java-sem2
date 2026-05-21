package movieapp.command.handlers;

import movieapp.command.AbstractCommand;
import movieapp.command.CommandContext;
import movieapp.command.CommandRegistry;

/**
 * Печатает справку.
 */
public class HelpCommand extends AbstractCommand {
    private final CommandRegistry registry;

    public HelpCommand(CommandRegistry registry) {
        super("help", "help : вывести справку по доступным командам");
        this.registry = registry;
    }

    @Override
    public void execute(CommandContext context, String arguments) {
        registry.all().forEach(cmd -> context.output().println(cmd.name()+description()));
    }
}
