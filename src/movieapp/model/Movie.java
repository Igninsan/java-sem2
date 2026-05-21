package movieapp.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Фильм коллекции.
 */
public class Movie implements Comparable<Movie> {
    private final int id;
    private String name;
    private Coordinates coordinates;
    private final ZonedDateTime creationDate;
    private int oscarsCount;
    private int goldenPalmCount;
    private Double totalBoxOffice;
    private MovieGenre genre;
    private Person operator;

    /**
     * Создаёт фильм.
     */
    public Movie(int id,
                 String name,
                 Coordinates coordinates,
                 ZonedDateTime creationDate,
                 int oscarsCount,
                 int goldenPalmCount,
                 Double totalBoxOffice,
                 MovieGenre genre,
                 Person operator) {
        if (id <= 0) {
            throw new IllegalArgumentException("id должен быть больше 0");
        }
        this.id = id;
        this.name = requireText(name, "name");
        this.coordinates = Objects.requireNonNull(coordinates, "coordinates не может быть null");
        this.creationDate = Objects.requireNonNull(creationDate, "creationDate не может быть null");
        if (oscarsCount <= 0) {
            throw new IllegalArgumentException("oscarsCount должен быть больше 0");
        }
        if (goldenPalmCount <= 0) {
            throw new IllegalArgumentException("goldenPalmCount должен быть больше 0");
        }
        if (totalBoxOffice == null || totalBoxOffice <= 0) {
            throw new IllegalArgumentException("totalBoxOffice должен быть больше 0");
        }
        this.oscarsCount = oscarsCount;
        this.goldenPalmCount = goldenPalmCount;
        this.totalBoxOffice = totalBoxOffice;
        this.genre = genre;
        this.operator = operator;
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Поле " + field + " не может быть пустым");
        }
        return value;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public int getOscarsCount() {
        return oscarsCount;
    }

    public int getGoldenPalmCount() {
        return goldenPalmCount;
    }

    public Double getTotalBoxOffice() {
        return totalBoxOffice;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public Person getOperator() {
        return operator;
    }

    public void setName(String name) {
        this.name = requireText(name, "name");
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = Objects.requireNonNull(coordinates, "coordinates не может быть null");
    }

    public void setOscarsCount(int oscarsCount) {
        if (oscarsCount <= 0) {
            throw new IllegalArgumentException("oscarsCount должен быть больше 0");
        }
        this.oscarsCount = oscarsCount;
    }

    public void setGoldenPalmCount(int goldenPalmCount) {
        if (goldenPalmCount <= 0) {
            throw new IllegalArgumentException("goldenPalmCount должен быть больше 0");
        }
        this.goldenPalmCount = goldenPalmCount;
    }

    public void setTotalBoxOffice(Double totalBoxOffice) {
        if (totalBoxOffice == null || totalBoxOffice <= 0) {
            throw new IllegalArgumentException("totalBoxOffice должен быть больше 0");
        }
        this.totalBoxOffice = totalBoxOffice;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public void setOperator(Person operator) {
        this.operator = operator;
    }

    @Override
    public int compareTo(Movie other) {
        if (other == null) {
            return 1;
        }
        int byBoxOffice = Double.compare(this.totalBoxOffice, other.totalBoxOffice);
        if (byBoxOffice != 0) {
            return byBoxOffice;
        }
        int byPalm = Integer.compare(this.goldenPalmCount, other.goldenPalmCount);
        if (byPalm != 0) {
            return byPalm;
        }
        int byOscars = Integer.compare(this.oscarsCount, other.oscarsCount);
        if (byOscars != 0) {
            return byOscars;
        }
        return Integer.compare(this.id, other.id);
    }

    /**
     * Возвращает копию с сохранением id и даты создания.
     */
    public Movie copyWithUpdatedFields(Movie other) {
        Objects.requireNonNull(other, "other не может быть null");
        return new Movie(
                this.id,
                other.name,
                other.coordinates,
                this.creationDate,
                other.oscarsCount,
                other.goldenPalmCount,
                other.totalBoxOffice,
                other.genre,
                other.operator
        );
    }

    @Override
    public String toString() {
        return "Movie{id=" + id + ", name='" + name + "', coordinates=" + coordinates
                + ", creationDate=" + creationDate + ", oscarsCount=" + oscarsCount
                + ", goldenPalmCount=" + goldenPalmCount + ", totalBoxOffice=" + totalBoxOffice
                + ", genre=" + genre + ", operator=" + operator + '}';
    }
}
