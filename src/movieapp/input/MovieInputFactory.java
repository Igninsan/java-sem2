package movieapp.input;

import movieapp.model.Coordinates;
import movieapp.model.Location;
import movieapp.model.Movie;
import movieapp.model.MovieGenre;
import movieapp.model.Person;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Создаёт фильмы из последовательного ввода.
 */
public class MovieInputFactory {
    private final FieldReader reader;

    public MovieInputFactory(FieldReader reader) {
        this.reader = Objects.requireNonNull(reader, "reader не может быть null");
    }

    public Movie createMovie(int id, ZonedDateTime creationDate) {
        String name = reader.readRequiredString("Введите name: ");
        Coordinates coordinates = readCoordinates();
        int oscarsCount = reader.readPositiveInt("Введите oscarsCount: ");
        int goldenPalmCount = reader.readPositiveInt("Введите goldenPalmCount: ");
        double totalBoxOffice = reader.readPositiveDouble("Введите totalBoxOffice: ");
        MovieGenre genre = (MovieGenre) reader.readEnumOrNull("Введите genre (или пустую строку): ", MovieGenre.class);
        Person operator = readOperator();
        return new Movie(id, name, coordinates, creationDate, oscarsCount, goldenPalmCount, totalBoxOffice, genre, operator);
    }

    private Coordinates readCoordinates() {
        Long x = reader.readLong("Введите coordinates.x: ");
        Integer y = reader.readInteger("Введите coordinates.y: ");
        return new Coordinates(x, y);
    }

    private Person readOperator() {
        String name = reader.readNullableString("Введите operator.name (или пустую строку): ");
        if (name == null) {
            return null;
        }
        int height = reader.readPositiveInt("Введите operator.height: ");
        double weight = reader.readPositiveDouble("Введите operator.weight: ");
        String passport = reader.readNullableString("Введите operator.passportID (или пустую строку): ");
        Location location = readLocation();
        return new Person(name, height, weight, passport, location);
    }

    private Location readLocation() {
        long x = reader.readLong("Введите operator.location.x: ");
        long y = reader.readLong("Введите operator.location.y: ");
        Float z = reader.readFloat("Введите operator.location.z: ");
        return new Location(x, y, z);
    }
}
