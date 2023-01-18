package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Project;

public class AddProject implements Command{
    private DataAccessObject<Project> dao = new DataAccessObject<>();
    @Override
    public String getCommand() {
        return "Dodaj projekt";
    }

    @Override
    public void service() {
        System.out.println("Podaj nazwe projektu:");
        String name = Command.scanner.nextLine();
        System.out.println("Podaj opis projektu:");
        String desc = Command.scanner.nextLine();

        Project project = Project.builder()
                .nazwa(name)
                .opis(desc)
                .build();

        dao.insert(project);
    }
}
