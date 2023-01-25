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
                new FindUsersInZadanie(),
                new FindZadanieByName(),
                new FindZadanieInProject(),
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
