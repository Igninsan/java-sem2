package movieapp.io;

import movieapp.model.Movie;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

/**
 * CSV-хранилище на базе файла.
 */
public class CsvMovieFileStorage implements CsvMovieStorage {
    private final Path filePath;
    private final CsvMovieCodec codec = new CsvMovieCodec();

    public CsvMovieFileStorage(Path filePath) {
        this.filePath = Objects.requireNonNull(filePath, "filePath не может быть null");
    }

    @Override
    public List<Movie> readAll() {
        List<Movie> movies = new ArrayList<>();
        File file = filePath.toFile();
        if (!file.exists()) {
            return movies;
        }
        if (!file.isFile()) {
            throw new CsvMovieStorageException("Путь указывает не на файл: " + filePath);
        }
        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                movies.add(codec.decode(line));
            }
        } catch (IOException ex) {
            throw new CsvMovieStorageException("Не удалось прочитать файл: " + filePath, ex);
        }
        return movies;
    }

    @Override
    public void writeAll(Collection<Movie> movies) {
        try {
            Path parent = filePath.toAbsolutePath().getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
                for (Movie movie : movies) {
                    writer.write(codec.encode(movie));
                    writer.newLine();
                }
            }
        } catch (IOException ex) {
            throw new CsvMovieStorageException("Не удалось записать файл: " + filePath, ex);
        }
    }

    @Override
    public Path getFilePath() {
        return filePath;
    }
}
