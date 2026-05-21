package movieapp.io;

import movieapp.model.Coordinates;
import movieapp.model.Location;
import movieapp.model.Movie;
import movieapp.model.MovieGenre;
import movieapp.model.Person;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Кодек для преобразования фильма в CSV и обратно.
 */
public class CsvMovieCodec {
    private static final int COLUMN_COUNT = 16;

    public String encode(Movie movie) {
        List<String> columns = new ArrayList<>(COLUMN_COUNT);
        columns.add(String.valueOf(movie.getId()));
        columns.add(movie.getName());
        columns.add(String.valueOf(movie.getCoordinates().getX()));
        columns.add(String.valueOf(movie.getCoordinates().getY()));
        columns.add(movie.getCreationDate().toString());
        columns.add(String.valueOf(movie.getOscarsCount()));
        columns.add(String.valueOf(movie.getGoldenPalmCount()));
        columns.add(String.valueOf(movie.getTotalBoxOffice()));
        columns.add(movie.getGenre() == null ? "" : movie.getGenre().name());

        Person op = movie.getOperator();
        if (op == null) {
            columns.add("");
            columns.add("");
            columns.add("");
            columns.add("");
            columns.add("");
            columns.add("");
            columns.add("");
        } else {
            columns.add(op.getName());
            columns.add(String.valueOf(op.getHeight()));
            columns.add(String.valueOf(op.getWeight()));
            columns.add(op.getPassportID() == null ? "" : op.getPassportID());
            Location location = op.getLocation();
            columns.add(String.valueOf(location.getX()));
            columns.add(String.valueOf(location.getY()));
            columns.add(String.valueOf(location.getZ()));
        }

        return String.join(",", columns.stream().map(this::escape).toList());
    }

    public Movie decode(String line) {
        List<String> columns = parseCsv(line);
        if (columns.size() != COLUMN_COUNT) {
            throw new CsvMovieStorageException("Ожидалось 16 колонок CSV, получено " + columns.size());
        }
        try {
            int id = Integer.parseInt(columns.get(0));
            String name = emptyToNull(columns.get(1));
            Coordinates coordinates = new Coordinates(Long.parseLong(columns.get(2)), Integer.parseInt(columns.get(3)));
            ZonedDateTime creationDate = ZonedDateTime.parse(columns.get(4));
            int oscarsCount = Integer.parseInt(columns.get(5));
            int goldenPalmCount = Integer.parseInt(columns.get(6));
            Double totalBoxOffice = Double.parseDouble(columns.get(7));
            MovieGenre genre = columns.get(8).isBlank() ? null : MovieGenre.valueOf(columns.get(8));

            Person operator = null;
            if (!columns.get(9).isBlank()) {
                operator = new Person(
                        columns.get(9),
                        Integer.parseInt(columns.get(10)),
                        Double.parseDouble(columns.get(11)),
                        emptyToNull(columns.get(12)),
                        new Location(
                                Long.parseLong(columns.get(13)),
                                Long.parseLong(columns.get(14)),
                                Float.valueOf(columns.get(15))
                        )
                );
            }

            return new Movie(id, name, coordinates, creationDate, oscarsCount, goldenPalmCount, totalBoxOffice, genre, operator);
        } catch (RuntimeException ex) {
            throw new  CsvMovieStorageException("Некорректная строка CSV: " + line, ex);
        }
    }

    private String emptyToNull(String value) {
        return value == null || value.isBlank() ? null : value;
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        boolean needQuotes = value.contains(",") || value.contains("\"") || value.contains("\n") || value.contains("\r");
        String escaped = value.replace("\"", "\"\"");
        return needQuotes ? "\"" + escaped + "\"" : escaped;
    }

    private List<String> parseCsv(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean quoted = false;
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (quoted) {
                if (ch == '"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                        current.append('"');
                        i++;
                    } else {
                        quoted = false;
                    }
                } else {
                    current.append(ch);
                }
            } else {
                if (ch == '"') {
                    quoted = true;
                } else if (ch == ',') {
                    result.add(current.toString());
                    current.setLength(0);
                } else {
                    current.append(ch);
                }
            }
        }
        result.add(current.toString());
        return result;
    }
}
