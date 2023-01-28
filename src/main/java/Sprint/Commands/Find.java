package Sprint.Commands;

import java.util.List;

import static Sprint.Main.*;

public class Find implements Command{
    @Override
    public String getCommand() {
        return "Znajdz";
    }

    @Override
    public void service() {
        List<Command> commandList = List.of(
                new FindSprintInProject(),
                new FindTaskByName(),
                new FindTaskInProject(),
                new FindUserInProject(),
                new FindTaskByProgress(),
                new FindUsersInTask()
        );
        display(commandList,"return", "wroc");
    }
    }

