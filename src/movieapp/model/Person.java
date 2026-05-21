package movieapp.model;

import java.util.Objects;

/**
 * Человек, связанный с фильмом.
 */
public class Person {
    private final String name;
    private final int height;
    private final double weight;
    private final String passportID;
    private final Location location;

    /**
     * Создаёт человека.
     *
     * @param name имя, не null и не пустое
     * @param height рост, > 0
     * @param weight вес, > 0
     * @param passportID паспорт, может быть null
     * @param location локация, не null
     */
    public Person(String name, int height, double weight, String passportID, Location location) {
        this.name = requireText(name, "name");
        if (height <= 0) {
            throw new IllegalArgumentException("Рост должен быть больше 0");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Вес должен быть больше 0");
        }
        this.height = height;
        this.weight = weight;
        this.passportID = passportID == null || passportID.isBlank() ? null : passportID;
        this.location = Objects.requireNonNull(location, "location не может быть null");
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Поле " + field + " не может быть пустым");
        }
        return value;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getPassportID() {
        return passportID;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', height=" + height + ", weight=" + weight
                + ", passportID='" + passportID + "', location=" + location + '}';
    }
}
