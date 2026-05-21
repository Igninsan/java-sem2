package movieapp.input;

/**
 * Читает значения полей с валидацией.
 */
public interface FieldReader {
    String readRequiredString(String prompt);

    String readNullableString(String prompt);

    int readPositiveInt(String prompt);

    long readLong(String prompt);

    int readInteger(String prompt);

    float readFloat(String prompt);

    double readPositiveDouble(String prompt);

    Long readNullableLong(String prompt);

    Integer readNullableInteger(String prompt);

    Float readNullableFloat(String prompt);

    Enum<?> readEnumOrNull(String prompt, Class<? extends Enum<?>> enumType);
}
