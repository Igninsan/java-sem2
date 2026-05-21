package movieapp.console;

/**
 * Источник строк для консоли или скрипта.
 */
public interface ConsoleInput {
    String readLine();

    boolean isInteractive();
}
