package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.Database.HibernateUtil;
import Sprint.model.Project;
import Sprint.model.Sprint;
import Sprint.model.User;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FindUserInProject implements Command{
    private DataAccessObject<Project> daoProject = new DataAccessObject<>();
    @Override
    public String getCommand() {
        return "Znajdz uzytkownikow w projekcie";
    }

    public FindUserInProject() {
        this.daoProject = daoProject;
    }

    @Override
    public void service() {
        System.out.println("Podaj id projektu");
        String id = scanner.nextLine();
        Long projectId = Long.parseLong(id);
        Optional<Project> optionalProject = daoProject.find(Project.class, projectId);
        if(optionalProject.isEmpty()){
            System.err.println("Id projektu nie istnieje!");
            return;
        }
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Project project = session.get(Project.class, projectId);
            Set<User> userSet = project.getUsers();
            System.out.println("Lista uzytkownikow w projekcie o id: " + id);
            userSet.forEach(System.out::println);
        }
        }
    }

