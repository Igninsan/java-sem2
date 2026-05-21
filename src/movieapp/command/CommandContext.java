package movieapp.command;

import movieapp.collection.MovieRepository;
import movieapp.console.ConsoleInput;
import movieapp.console.ConsoleOutput;
import movieapp.input.FieldReader;
import movieapp.script.ScriptExecutor;

import java.nio.file.Path;
import java.util.Deque;
import java.util.Objects;

/**
 * Контекст выполнения команды.
 */
public class CommandContext {
    private final MovieRepository repository;
    private final ConsoleInput input;
    private final ConsoleOutput output;
    private final FieldReader fieldReader;
    private final ScriptExecutor scriptExecutor;
    private final Deque<String> history;
    private final boolean interactive;
    private final Path currentScriptPath;
    private final int depth;

    public CommandContext(MovieRepository repository,
                          ConsoleInput input,
                          ConsoleOutput output,
                          FieldReader fieldReader,
                          ScriptExecutor scriptExecutor,
                          Deque<String> history,
                          boolean interactive,
                          Path currentScriptPath, int depth) {
        this.repository = Objects.requireNonNull(repository, "repository не может быть null");
        this.input = Objects.requireNonNull(input, "input не может быть null");
        this.output = Objects.requireNonNull(output, "output не может быть null");
        this.fieldReader = Objects.requireNonNull(fieldReader, "fieldReader не может быть null");
        this.scriptExecutor = Objects.requireNonNull(scriptExecutor, "scriptExecutor не может быть null");
        this.history = Objects.requireNonNull(history, "history не может быть null");
        this.interactive = interactive;
        this.currentScriptPath = currentScriptPath;
        this.depth = depth;
    }

    public MovieRepository repository() {
        return repository;
    }

    public ConsoleInput input() {
        return input;
    }

    public ConsoleOutput output() {
        return output;
    }

    public FieldReader fieldReader() {
        return fieldReader;
    }

    public ScriptExecutor scriptExecutor() {
        return scriptExecutor;
    }

    public Deque<String> history() {
        return history;
    }

    public boolean interactive() {
        return interactive;
    }

    public Path currentScriptPath() {
        return currentScriptPath;
    }

    public int getDepth() {
        return depth;
    }
}
