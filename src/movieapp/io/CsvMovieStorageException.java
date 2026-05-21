package movieapp.io;

/**
 * Исключение, связанное с CSV-хранилищем.
 */
public class CsvMovieStorageException extends RuntimeException {
    public CsvMovieStorageException(String message) {
        super(message);
    }

    public CsvMovieStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
