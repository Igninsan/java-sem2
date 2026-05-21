package movieapp;

import movieapp.collection.MovieStackRepository;
import movieapp.console.InteractiveConsoleInput;
import movieapp.console.StdConsoleOutput;
import movieapp.io.CsvMovieFileStorage;

import java.nio.file.Path;
import java.util.Scanner;

/**
 * Точка входа.
 */
public class Main {
    public static void main(String[] args) {
        String env = System.getenv("MOVIES_FILE");
        Path file = Path.of(env == null || env.isBlank() ? "movies.csv" : env);
        var storage = new CsvMovieFileStorage(file);
        var repository = new MovieStackRepository(storage);
        var app = new ConsoleApplication(repository, new InteractiveConsoleInput(new Scanner(System.in)), new StdConsoleOutput());
        app.run();
    }
}
