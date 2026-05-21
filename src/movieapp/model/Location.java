package movieapp.model;

import java.util.Objects;

/**
 * Локация человека.
 */
public class Location {
    private final long x;
    private final long y;
    private final Float z;

    /**
     * Создаёт локацию.
     *
     * @param x координата X
     * @param y координата Y
     * @param z координата Z, не null
     */
    public Location(long x, long y, Float z) {
        this.x = x;
        this.y = y;
        this.z = Objects.requireNonNull(z, "z не может быть null");
    }

    /**
     * Возвращает X.
     */
    public long getX() {
        return x;
    }

    /**
     * Возвращает Y.
     */
    public long getY() {
        return y;
    }

    /**
     * Возвращает Z.
     */
    public Float getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Location{x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}
