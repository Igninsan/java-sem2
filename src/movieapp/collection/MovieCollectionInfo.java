package movieapp.collection;

import java.time.LocalDateTime;

/**
 * Информация о коллекции.
 */
public class MovieCollectionInfo {
    private final String type;
    private final LocalDateTime initializedAt;
    private final int size;
    private final String sourceFile;

    public MovieCollectionInfo(String type, LocalDateTime initializedAt, int size, String sourceFile) {
        this.type = type;
        this.initializedAt = initializedAt;
        this.size = size;
        this.sourceFile = sourceFile;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getInitializedAt() {
        return initializedAt;
    }

    public int getSize() {
        return size;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    @Override
    public String toString() {
        return "Тип коллекции: " + type + System.lineSeparator()
                + "Дата инициализации: " + initializedAt + System.lineSeparator()
                + "Размер: " + size + System.lineSeparator()
                + "Файл: " + sourceFile;
    }
}
