package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Project;

import java.util.List;

public class ListProject implements Command {
    private DataAccessObject<Project> dao;
    public ListProject(){
        this.dao = new DataAccessObject<>();
    }
    @Override
    public String getCommand() {
        return "Lista projektow";
    }

    @Override
    public void service() {
        List<Project> projects = dao.findAll(Project.class);
        projects.forEach(System.out::println);
    }
}
