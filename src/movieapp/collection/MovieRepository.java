package movieapp.collection;

import movieapp.model.Movie;
import movieapp.model.MovieGenre;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий фильмов.
 */
public interface MovieRepository {
    List<Movie> findAll();

    Optional<Movie> findById(int id);

    Movie add(Movie movie);

    Movie update(int id, Movie movie);

    boolean removeById(int id);

    boolean removeAt(int index);

    void clear();

    int size();

    Movie getMaxByGoldenPalmCount();

    long countLessThanOperator(String operatorName);

    long countGreaterThanGenre(MovieGenre genre);

    MovieCollectionInfo info();

    void sort();

    int nextId();

    void loadFromStorage();

    void saveToStorage();

    long getAverageOscar();
}
