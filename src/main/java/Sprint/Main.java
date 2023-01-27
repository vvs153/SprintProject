package Sprint;

import Sprint.Commands.*;

import java.util.List;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_BLUE = "\u001B[34m";

    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
       // new DataAccessObject<Project>().findAll(Project.class);
        List<Command> commandList = List.of(
                new Update(),
                new Add(),
                new Lists(),
                new Delete(),
                new Find()

        );
        display(commandList,"exit","wyjdz");
    }
    public static void display(List<Command> list, String e1,String e2){
        String command;
        do {
            System.out.println(ANSI_RED+"\nAplikacja do zarzadzania projektami\n"+ANSI_RESET);
            System.out.println(ANSI_CYAN+"Lista dostepnych komand: ");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i).getCommand());
            }
            System.out.println(ANSI_RESET);

            System.out.println(ANSI_BLUE+"Podaj komende"+ANSI_RESET);
            command = Command.scanner.nextLine();

            for (Command availableCommand : list) {
                if (availableCommand.getCommand().equalsIgnoreCase(command)) {
                    availableCommand.service();
                }
            }
        } while (!command.equalsIgnoreCase(e1)&&!command.equalsIgnoreCase(e2));
    }
}
