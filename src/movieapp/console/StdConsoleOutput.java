package movieapp.console;

/**
 * Реализация вывода в стандартный поток.
 */
public class StdConsoleOutput implements ConsoleOutput {
    @Override
    public void print(String text) {
        System.out.print(text);
    }

    @Override
    public void println(String text) {
        System.out.println(text);
    }
}
