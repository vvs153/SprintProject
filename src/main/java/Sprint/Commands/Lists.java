package Sprint.Commands;

import java.util.List;

import static Sprint.Main.*;

public class Lists implements Command{
    @Override
    public String getCommand() {
        return "Listy";
    }

    @Override
    public void service() {
       List<Command> commandList = List.of(
                new ListProject(),
                new ListSprint(),
                new ListUser(),
                new ListTask()

        );
        display(commandList,"return", "wroc");
    }
    }

