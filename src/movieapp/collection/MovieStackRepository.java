package movieapp.collection;

import movieapp.io.CsvMovieStorage;
import movieapp.model.Movie;
import movieapp.model.MovieGenre;
import movieapp.model.Person;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Реализация репозитория на Stack.
 */
public class MovieStackRepository implements MovieRepository {
    private final List<Movie> stack = new Stack<>();
    private final CsvMovieStorage storage;
    private final LocalDateTime initializedAt = LocalDateTime.now();
    private int nextId = 0;

    public MovieStackRepository(CsvMovieStorage storage) {
        this.storage = Objects.requireNonNull(storage, "storage не может быть null");
    }

    @Override
    public List<Movie> findAll() {
        return new ArrayList<>(stack);
    }

    @Override
    public Optional<Movie> findById(int id) {
        for (Movie m : stack) {
            if (m.getId() == id) return Optional.of(m);
        }
        return Optional.empty();
    }

    @Override
    public Movie add(Movie movie) {
        Objects.requireNonNull(movie);
        stack.add(movie);
        sort();
        if (movie.getId() > nextId) nextId = movie.getId();
        return movie;
    }

    @Override
    public Movie update(int id, Movie movie) {
        Objects.requireNonNull(movie);
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i).getId() == id) {
                Movie updated = stack.get(i).copyWithUpdatedFields(movie);
                stack.set(i, updated);
                sort();
                return updated;
            }
        }
        return null;
    }

    @Override
    public boolean removeById(int id) {
        return stack.removeIf(m -> m.getId() == id);
    }

    @Override
    public boolean removeAt(int index) {
        if (index < 0 || index >= stack.size()) return false;
        stack.remove(index);
        return true;
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public Movie getMaxByGoldenPalmCount() {
        if (stack.isEmpty()) return null;
        Movie max = stack.get(0);
        for (Movie m : stack) {
            if (m.getGoldenPalmCount() > max.getGoldenPalmCount()) max = m;
        }
        return max;
    }

    @Override
    public long countLessThanOperator(String operatorName) {
        if (operatorName == null) return 0;
        long count = 0;
        for (Movie m : stack) {
            Person op = m.getOperator();
            if (op != null && op.getName().compareTo(operatorName) < 0) count++;
        }
        return count;
    }

    @Override
    public long countGreaterThanGenre(MovieGenre genre) {
        if (genre == null) return 0;
        long count = 0;
        for (Movie m : stack) {
            MovieGenre g = m.getGenre();
            if (g != null && g.ordinal() > genre.ordinal()) count++;
        }
        return count;
    }

    @Override
    public long getAverageOscar() {
        long cnt = 0;
        for (Movie m : stack) {
            cnt += m.getOscarsCount();
        }
        return cnt / stack.size();
    }

    @Override
    public MovieCollectionInfo info() {
        return new MovieCollectionInfo("java.util.Stack<Movie>", initializedAt, stack.size(), storage.getFilePath().toString());
    }

    @Override
    public void sort() {
        stack.sort(Movie::compareTo);
    }

    @Override
    public int nextId() {
        return ++nextId;
    }

    @Override
    public void loadFromStorage() {
        stack.clear();
        stack.addAll(storage.readAll());
        sort();
        nextId = 0;
        for (Movie m : stack) {
            if (m.getId() > nextId) nextId = m.getId();
        }
    }

    @Override
    public void saveToStorage() {
        storage.writeAll(stack);
    }
}