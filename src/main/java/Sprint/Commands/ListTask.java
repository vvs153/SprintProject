package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Task;

import java.util.List;

public class ListTask implements Command {
    private DataAccessObject<Task> dao;
    public ListTask(){
        this.dao = new DataAccessObject<>();
    }
    @Override
    public String getCommand() {
        return "Lista zadan";
    }

    @Override
    public void service() {
        List<Task> zadania = dao.findAll(Task.class);
        zadania.forEach(System.out::println);
    }
}
