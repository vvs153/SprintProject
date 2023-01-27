package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.*;

import java.time.LocalDate;
import java.util.Optional;

public class UpdateZadanie implements Command{
    private DataAccessObject<Project> daoProject = new DataAccessObject<>();
    private DataAccessObject<User> daoUser = new DataAccessObject<>();
    private DataAccessObject<Zadanie> daoZadanie = new DataAccessObject<>();

    public UpdateZadanie() {
        this.daoProject = daoProject;
        this.daoUser = daoUser;
        this.daoZadanie = daoZadanie;
    }

    @Override
    public String getCommand() {
        return "Aktualizuj zadanie";
    }

    @Override
    public void service() {
        System.out.println("Podaj id zadania:");
        String idZadanie = Command.scanner.nextLine();
        Long id = Long.parseLong(idZadanie);
        if(!daoZadanie.exists(Zadanie.class,id)){
            System.err.println("Zadanie o podanym id nie istnieje!");
            return;
        }
        System.out.println("Podaj id uzytkownika:");
        String idUserString = Command.scanner.nextLine();
        Long idUser = Long.parseLong(idUserString);
        Optional<User> userOptional = daoUser.find(User.class, idUser);
        if (userOptional.isEmpty()) {
            System.err.println("Uzytkownik o podanym id nie istnieje!");
            return;
        }
        System.out.println("Podaj id projektu:");
        String idProjectString = Command.scanner.nextLine();
        Long idProject = Long.parseLong(idProjectString);
        Optional<Project> projectOptional = daoProject.find(Project.class, idProject);
        if (projectOptional.isEmpty()) {
            System.err.println("Projekt o podanym id nie istnieje!");
            return;
        }
        System.out.println("Podaj nazwe zadania:");
        String name = Command.scanner.nextLine();
        System.out.println("Podaj opis zadania:");
        String desc = Command.scanner.nextLine();
        System.out.println("Podaj wage zadania:");
        String weightString = Command.scanner.nextLine();
        int weight = Integer.parseInt(weightString);
        System.out.println("Podaj punkty zadania:");
        String pointsString = Command.scanner.nextLine();
        int points = Integer.parseInt(pointsString);
        System.out.println("Podaj stan zadania (BACKLOG, TODO, IN_PROGRESS, QA, DONE):");
        String progressString = scanner.nextLine().toUpperCase();
        Progress progress = Progress.valueOf(progressString);

        Zadanie zadanie = Zadanie.builder()
                .id(id)
                .project(projectOptional.get())
                .user(userOptional.get())
                .nazwa(name)
                .opis(desc)
                .waga(weight)
                .points(points)
                .progress(progress)
                .build();
        daoZadanie.update(Zadanie.class,id,zadanie);
    }
}
