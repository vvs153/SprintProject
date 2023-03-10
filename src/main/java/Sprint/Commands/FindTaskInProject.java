package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.Database.HibernateUtil;
import Sprint.model.Project;
import Sprint.model.Task;
import org.hibernate.Session;

import java.util.Optional;
import java.util.Set;

public class FindTaskInProject implements Command{
    private DataAccessObject<Project> daoProject = new DataAccessObject<>();
    @Override
    public String getCommand() {
        return "Znajdz zadania w projekcie";
    }

    public FindTaskInProject() {
        this.daoProject = daoProject;
    }

    @Override
    public void service() {
        System.out.println("Podaj id projektu:");
        String id = scanner.nextLine();
        Long projectId = Long.parseLong(id);
        Optional<Project> optionalProject = daoProject.find(Project.class, projectId);
        if(optionalProject.isEmpty()){
            System.err.println("Projekt o podanym id nie istnieje!");
            return;
        }
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Project project = session.get(Project.class, projectId);
            Set<Task> zadania = project.getTask2Set();
            System.out.println("Lista zadan w projekcie o id: " + id);
            zadania.forEach(System.out::println);
        }
        }
    }

