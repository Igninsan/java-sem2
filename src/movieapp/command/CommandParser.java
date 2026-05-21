package movieapp.command;

import java.util.Objects;

/**
 * Парсер команды из строки.
 */
public class CommandParser {
    public ParsedCommand parse(String line) {
        Objects.requireNonNull(line, "line не может быть null");
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return new ParsedCommand("", "");
        }
        int idx = trimmed.indexOf(' ');
        if (idx < 0) {
            return new ParsedCommand(trimmed, "");
        }
        return new ParsedCommand(trimmed.substring(0, idx), trimmed.substring(idx + 1).trim());
    }

    /**
     * Распарсенная команда.
     */
    public record ParsedCommand(String name, String arguments) {
    }
}
