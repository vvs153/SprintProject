package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.Database.HibernateUtil;
import Sprint.model.Project;
import Sprint.model.User;
import org.hibernate.Session;

import java.util.Optional;
import java.util.Set;

public class AddUserToTask implements Command{
    private DataAccessObject<User> dao = new DataAccessObject<>();
    private DataAccessObject<Project> daoProject = new DataAccessObject<>();

    public AddUserToTask() {
        this.dao = dao;
        this.daoProject = daoProject;
    }

    @Override
    public String getCommand() {
        return "Dodaj uzytkownika do projektu";
    }

    @Override
    public void service() {
        System.out.println("Podaj id uzytkownika:");
        String idString = Command.scanner.nextLine();
        Long id = Long.parseLong(idString);
        Optional<User> optionalUser = dao.find(User.class, id);
        if(optionalUser.isEmpty()){
            System.err.println("Uzytkownik o podanym id nie istnieje!");
            return;
        }
        System.out.println("Podaj id projektu:");
        String idProjectString = Command.scanner.nextLine();
        Long idProject = Long.parseLong(idProjectString);
        Optional<Project> optionalProject = daoProject.find(Project.class, idProject);
        if(optionalProject.isEmpty()){
            System.err.println("Projekt o podanym id nie istnieje!");
            return;
        }
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            Project project = session.get(Project.class,idProject);
        /*    Set<Project> projectSet = optionalUser.get().getProjects();
            projectSet.add(optionalProject.get());
            Set<User> usersSet = optionalProject.get().getUsers();
            usersSet.add(optionalUser.get()); */
            Set<Project> projectSet = user.getProjects();
            projectSet.add(project);
            Set<User> usersSet = project.getUsers();
            usersSet.add(user);
            User u = User.builder()
                    .id(id)
                    .imie(optionalUser.get().getImie())
                    .nazwisko(optionalUser.get().getNazwisko())
                    .nick(optionalUser.get().getNick())
                    .email(optionalUser.get().getEmail())
                    .projects(projectSet)
                    .build();
            dao.update(User.class,id,u);
            Project p = Project.builder()
                    .id(idProject)
                    .nazwa(optionalProject.get().getNazwa())
                    .opis(optionalProject.get().getOpis())
                    .users(usersSet)
                    .build();
            daoProject.update(Project.class,idProject,p);
        }








    }
}
