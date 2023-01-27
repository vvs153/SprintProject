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
                new FindUsersInZadanie(),
                new FindZadanieByName(),
                new FindZadanieInProject(),
                new FindSprintInProject(),
                new FindUserInProject(),
                new FindZadanieByProgress()

        );
        display(commandList,"return", "wroc");
    }
    }

