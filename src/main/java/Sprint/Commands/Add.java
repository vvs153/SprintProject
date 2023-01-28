package Sprint.Commands;

import java.util.List;

import static Sprint.Main.*;

public class Add implements Command{
    @Override
    public String getCommand() {
        return "Dodaj";
    }

    @Override
    public void service() {
        List<Command> commandList = List.of(
                new AddProject(),
                new AddSprint(),
                new AddUser(),
                new AddTask(),
                new AddUserToProject()

        );
        display(commandList,"return", "wroc");
    }
    }

