package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.Database.HibernateUtil;
import Sprint.model.Project;
import Sprint.model.Sprint;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class FindSprintInProject implements Command{
    private DataAccessObject<Project> daoProject = new DataAccessObject<>();
    @Override
    public String getCommand() {
        return "Znajdz sprinty w projekcie";
    }

    public FindSprintInProject() {
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
            List<Sprint> sprinty = project.getTask2Set().stream()
                    .flatMap(zadanie -> zadanie.getSprintSet().stream())
                    .distinct().toList();
            System.out.println("Lista sprintow w projekcie o id: " + id);
            sprinty.forEach(System.out::println);
        }
        }
    }

