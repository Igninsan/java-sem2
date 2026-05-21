package movieapp.model;

import java.util.Objects;

/**
 * Координаты фильма.
 */
public class Coordinates {
    private final Long x;
    private final Integer y;

    /**
     * Создаёт координаты.
     *
     * @param x координата X, не null
     * @param y координата Y, не null
     */
    public Coordinates(Long x, Integer y) {
        this.x = Objects.requireNonNull(x, "x не может быть null");
        this.y = Objects.requireNonNull(y, "y не может быть null");
    }

    public Long getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{x=" + x + ", y=" + y + '}';
    }
}
