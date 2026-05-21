package movieapp.console;

import java.util.List;
import java.util.Objects;

/**
 * Ввод строк из скрипта.
 */
public class ScriptConsoleInput implements ConsoleInput {
    private final List<String> lines;
    private int index = 0;

    public ScriptConsoleInput(List<String> lines) {
        this.lines = Objects.requireNonNull(lines, "lines не может быть null");
    }

    @Override
    public String readLine() {
        if (index >= lines.size()) {
            return null;
        }
        return lines.get(index++);
    }

    @Override
    public boolean isInteractive() {
        return false;
    }
}
