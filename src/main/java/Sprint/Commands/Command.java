package Sprint.Commands;

import java.util.Scanner;

public interface Command {
    Scanner scanner = new Scanner(System.in);

    String getCommand();
    void service();
}
