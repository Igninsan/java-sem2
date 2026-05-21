package movieapp.io;

import movieapp.model.Movie;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

/**
 * Хранилище фильмов в CSV.
 */
public interface CsvMovieStorage {
    List<Movie> readAll();

    void writeAll(Collection<Movie> movies);

    Path getFilePath();
}
