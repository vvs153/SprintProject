package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Progress;
import Sprint.model.Project;
import Sprint.model.User;
import Sprint.model.Task;

import java.util.Optional;

public class AddTask implements Command{
    private DataAccessObject<Task> dao = new DataAccessObject<>();
    private DataAccessObject<User> daoUser = new DataAccessObject<>();
    private DataAccessObject<Project> daoProject = new DataAccessObject<>();

    public AddTask() {
        this.dao = dao;
        this.daoUser = daoUser;
        this.daoProject = daoProject;
    }

    @Override
    public String getCommand() {
        return "Dodaj zadanie";
    }

    @Override
    public void service() {

        System.out.println("Podaj nazwe zadania:");
        String name = Command.scanner.nextLine();
        System.out.println("Podaj opis zadania:");
        String desc = Command.scanner.nextLine();

        System.out.println("Podaj id uzytkownika:");
        String idString = Command.scanner.nextLine();
        Long id = Long.parseLong(idString);
        Optional<User> optionalUser = daoUser.find(User.class, id);
        if(optionalUser.isEmpty()){
            System.err.println("Uzytkownik o podanym id nie istnieje!");
            return;
        }

        System.out.println("Podaj id projektu:");
        String idString2 = Command.scanner.nextLine();
        Long projectId = Long.parseLong(idString2);
        Optional<Project> optionalProject = daoProject.find(Project.class, projectId);
        if(optionalProject.isEmpty()){
            System.err.println("Projekt o podanym id nie istnieje!");
            return;
        }
        System.out.println("Podaj wage zadania:");
        String weightString = Command.scanner.nextLine();
        int weight = Integer.parseInt(weightString);
        System.out.println("Podaj punkty zadania:");
        String pointsString = Command.scanner.nextLine();
        int points = Integer.parseInt(pointsString);
        System.out.println("Podaj stan zadania (BACKLOG, TODO, IN_PROGRESS, QA, DONE):");
        String progressString = scanner.nextLine().toUpperCase();
        Progress progress = Progress.valueOf(progressString);

        Task task = Task.builder()
                .nazwa(name)
                .opis(desc)
                .project(optionalProject.get())
                .user(optionalUser.get())
                .waga(weight)
                .points(points)
                .progress(progress)
                .build();

        dao.insert(task);
    }
}
