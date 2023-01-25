package Sprint;

import Sprint.Commands.*;
import Sprint.Database.DataAccessObject;
import Sprint.model.Project;

import java.util.List;
import java.util.TreeSet;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static void main(String[] args) {
       // new DataAccessObject<Project>().findAll(Project.class);
        List<Command> commandList = List.of(
                new AddProject(),
                new AddSprint(),
                new AddUser(),
                new AddZadanie(),
                new AddUserdoZadania(),
                new ListProject(),
                new ListSprint(),
                new ListUser(),
                new ListZadanie(),
                new DeleteProject(),
                new DeleteSprint(),
                new DeleteUser(),
                new DeleteZadanie(),
                new DeleteZS(),
                new UpdateProject(),
                new UpdateSprint(),
                new UpdateUser(),
                new UpdateZadanie(),
                new FindZadanie(),
                new FindZadanieName(),
                new FindUsersZadanie(),
                new FindUsersZadanie2(),
                new FindZadanieName2(),
                new FindZadanie2(),
                new FindSprintInProject(),
                new FindUserInProject(),
                new DeletePZS()
        );
        String command;
        do {
            System.out.println(ANSI_RED+"\nAplikacja do zarzadzania projektami\n"+ANSI_RESET);
            System.out.println(ANSI_CYAN+"Lista dostepnych komand: ");
            for (int i = 0; i < commandList.size(); i++) {
                System.out.println((i + 1) + ". " + commandList.get(i).getCommand());
            }
            System.out.println(ANSI_RESET);

            System.out.println(ANSI_BLUE+"Podaj komende"+ANSI_RESET);
            command = Command.scanner.nextLine();

            for (Command availableCommand : commandList) {
                if (availableCommand.getCommand().equalsIgnoreCase(command)) {
                    availableCommand.service();
                }
            }
        } while (!command.equalsIgnoreCase("exit")&&!command.equalsIgnoreCase("wyjdz"));
    }
}
