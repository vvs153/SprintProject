package Sprint.Commands;

import java.util.List;

import static Sprint.Main.*;

public class Update implements Command{
    @Override
    public String getCommand() {
        return "Aktualizuj";
    }

    @Override
    public void service() {
        List<Command> commandList = List.of(
                new UpdateProject(),
                new UpdateSprint(),
                new UpdateUser(),
                new UpdateZadanie()

        );
        display(commandList,"return", "wroc");
    }
    }

