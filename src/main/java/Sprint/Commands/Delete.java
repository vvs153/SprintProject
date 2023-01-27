package Sprint.Commands;

import java.util.List;

import static Sprint.Main.*;

public class Delete implements Command{
    @Override
    public String getCommand() {
        return "Usun";
    }

    @Override
    public void service() {
        List<Command> commandList = List.of(
                new DeleteProject(),
                new DeleteSprint(),
                new DeleteUser(),
                new DeleteZadanie(),
                new DeleteZS(),
                new DeletePZS()

        );
        display(commandList,"return", "wroc");
    }
    }

