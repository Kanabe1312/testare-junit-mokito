package app.view;

import java.util.Scanner;

public abstract class BaseView {

    protected final Scanner scanner;

    protected BaseView() {
        this.scanner = new Scanner(System.in);
    }

    protected void runLoop() {
        boolean running = true;

        while (running) {
            showMenu();
            int option = readInt();
            running = handleOption(option);
            System.out.println();
        }
    }

    protected abstract void showMenu();

    protected abstract boolean handleOption(int option);

    protected String readLine() {
        return scanner.nextLine();
    }

    protected int readInt() {
        return Integer.parseInt(readLine());
    }

    protected void printInvalidOption() {
        System.out.println("Select a valid option!");
    }
}
