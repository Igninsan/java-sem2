package movieapp.input;

import movieapp.console.ConsoleInput;
import movieapp.console.ConsoleOutput;

import java.util.Arrays;
import java.util.Objects;

/**
 * Читает поля объекта из интерактивного или скриптового ввода.
 */
public class ConsoleFieldReader implements FieldReader {
    private final ConsoleInput input;
    private final ConsoleOutput output;

    public ConsoleFieldReader(ConsoleInput input, ConsoleOutput output) {
        this.input = Objects.requireNonNull(input);
        this.output = Objects.requireNonNull(output);
    }

    @Override
    public String readRequiredString(String prompt) {
        while (true) {
            String value = read(prompt);
            if (value != null && !value.isBlank()) return value;
            output.println("Ошибка: значение не может быть пустым.");
        }
    }

    @Override
    public String readNullableString(String prompt) {
        String value = read(prompt);
        return (value == null || value.isBlank()) ? null : value;
    }

    @Override
    public int readPositiveInt(String prompt) {
        while (true) {
            String value = read(prompt);
            try {
                int parsed = Integer.parseInt(value.trim());
                if (parsed > 0) return parsed;
                output.println("Ошибка: число должно быть больше 0.");
            } catch (NumberFormatException ex) {
                output.println("Ошибка: ожидается целое число.");
            }
        }
    }

    @Override
    public long readLong(String prompt) {
        while (true) {
            String value = read(prompt);
            if (value == null) return 0;
            try {
                return Long.parseLong(value.trim());
            } catch (NumberFormatException ex) {
                output.println("Ошибка: ожидается целое число.");
            }
        }
    }

    @Override
    public int readInteger(String prompt) {
        while (true) {
            String value = read(prompt);
            if (value == null) return 0;
            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException ex) {
                output.println("Ошибка: ожидается целое число.");
            }
        }
    }

    @Override
    public float readFloat(String prompt) {
        while (true) {
            String value = read(prompt);
            if (value == null) return 0f;
            try {
                return Float.parseFloat(value.trim());
            } catch (NumberFormatException ex) {
                output.println("Ошибка: ожидается число.");
            }
        }
    }

    @Override
    public double readPositiveDouble(String prompt) {
        while (true) {
            String value = read(prompt);
            if (value == null) return 0.0;
            try {
                double parsed = Double.parseDouble(value.trim());
                if (parsed > 0) return parsed;
                output.println("Ошибка: число должно быть больше 0.");
            } catch (NumberFormatException ex) {
                output.println("Ошибка: ожидается число.");
            }
        }
    }

    @Override
    public Long readNullableLong(String prompt) {
        while (true) {
            String value = read(prompt);
            if (value == null || value.isBlank()) return null;
            try {
                return Long.parseLong(value.trim());
            } catch (NumberFormatException ex) {
                output.println("Ошибка: ожидается целое число.");
            }
        }
    }

    @Override
    public Integer readNullableInteger(String prompt) {
        while (true) {
            String value = read(prompt);
            if (value == null || value.isBlank()) return null;
            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException ex) {
                output.println("Ошибка: ожидается целое число.");
            }
        }
    }

    @Override
    public Float readNullableFloat(String prompt) {
        while (true) {
            String value = read(prompt);
            if (value == null || value.isBlank()) return null;
            try {
                return Float.parseFloat(value.trim());
            } catch (NumberFormatException ex) {
                output.println("Ошибка: ожидается число.");
            }
        }
    }

    @Override
    public Enum<?> readEnumOrNull(String prompt, Class<? extends Enum<?>> enumType) {
        output.println("Доступные значения: " + Arrays.toString(enumType.getEnumConstants()));
        while (true) {
            String value = read(prompt);
            if (value == null || value.isBlank()) return null;
            try {
                @SuppressWarnings({"unchecked", "rawtypes"})
                Enum<?> result = Enum.valueOf((Class) enumType, value.trim());
                return result;
            } catch (IllegalArgumentException ex) {
                output.println("Ошибка: значение должно быть одним из перечисленных.");
            }
        }
    }

    private String read(String prompt) {
        if (input.isInteractive()) output.print(prompt);
        String line = input.readLine();
        if (line == null) {
            throw new RuntimeException("Ввод завершён.");
        }
        return line;
    }
}