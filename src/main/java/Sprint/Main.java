package Sprint;

import Sprint.Commands.*;
import Sprint.Database.DataAccessObject;
import Sprint.model.Project;

import java.util.List;

public class Main {
    public static void main(String[] args) {
       // new DataAccessObject<Project>().findAll(Project.class);
        List<Command> commandList = List.of(
                new AddProject(),
                new AddSprint(),
                new AddUser(),
                new AddZadanie(),
                new ListProject(),
                new ListSprint(),
                new ListUser(),
                new ListZadanie(),
                new DeleteSprint(),
                new DeleteUser()
        );
        String command;
        do {
            System.out.println("Aplikacja do zarzadzania projektami");
            System.out.println("Lista dostepnych komand: ");
            for (int i = 0; i < commandList.size(); i++) {
                System.out.println((i + 1) + ". " + commandList.get(i).getCommand());
            }
            System.out.println();

            System.out.println("Podaj komende");
            command = Command.scanner.nextLine();

            for (Command availableCommand : commandList) {
                if (availableCommand.getCommand().equalsIgnoreCase(command)) {
                    availableCommand.service();
                }
            }
        } while (!command.equalsIgnoreCase("exit"));
    }
}
