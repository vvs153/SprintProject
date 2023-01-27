package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Progress;
import Sprint.model.Project;
import Sprint.model.User;
import Sprint.model.Zadanie;

import java.util.Optional;

public class UpdateProject implements Command{
    private DataAccessObject<Project> daoProject = new DataAccessObject<>();
    private DataAccessObject<User> daoUser = new DataAccessObject<>();
    private DataAccessObject<Zadanie> daoZadanie = new DataAccessObject<>();

    public UpdateProject() {
        this.daoProject = daoProject;
        this.daoUser = daoUser;
        this.daoZadanie = daoZadanie;
    }

    @Override
    public String getCommand() {
        return "Aktualizuj projekt";
    }

    @Override
    public void service() {

        System.out.println("Podaj id projektu:");
        String idProjectString = Command.scanner.nextLine();
        Long idProject = Long.parseLong(idProjectString);
        Optional<Project> projectOptional = daoProject.find(Project.class, idProject);
        if (projectOptional.isEmpty()) {
            System.err.println("Projekt o podanym id nie istnieje!");
            return;
        }
        System.out.println("Podaj nazwe projektu:");
        String name = Command.scanner.nextLine();
        System.out.println("Podaj opis projektu:");
        String desc = Command.scanner.nextLine();

        Project project = Project.builder()
                .id(idProject)
                .nazwa(name)
                .opis(desc)
                .build();
        daoProject.update(Project.class,idProject,project);
    }
}
