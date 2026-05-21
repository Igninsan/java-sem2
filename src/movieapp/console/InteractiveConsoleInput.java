package movieapp.console;

import java.util.Scanner;

/**
 * Интерактивный ввод из System.in.
 */
public class InteractiveConsoleInput implements ConsoleInput {
    private final Scanner scanner;

    public InteractiveConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readLine() {
        if (!scanner.hasNextLine()) {
            return null;
        }
        return scanner.nextLine();
    }

    @Override
    public boolean isInteractive() {
        return true;
    }
}
