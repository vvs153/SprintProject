package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Project;
import Sprint.model.Sprint;

import java.util.List;



public class ListSprint implements Command {
    private DataAccessObject<Sprint> dao;
    public ListSprint(){
        this.dao = new DataAccessObject<>();
    }
    @Override
    public String getCommand() {
        return "Lista sprintow";
    }

    @Override
    public void service() {
        List<Sprint> sprints = dao.findAll(Sprint.class);
        sprints.forEach(System.out::println);
    }
}
