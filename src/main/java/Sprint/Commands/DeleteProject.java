package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Project;
import Sprint.model.Sprint;

public class DeleteProject implements Command{
    private DataAccessObject<Project> dao = new DataAccessObject<>();
    @Override
    public String getCommand() {
        return "Usun projekt";
    }

    @Override
    public void service() {
        System.out.println("Podaj id usuwanego projektu:");
        String id = scanner.nextLine();
        Long projectId = Long.parseLong(id);
        if(dao.delete(Project.class,projectId)){
            System.out.println("Usunieto projekt!");
        } else {
            System.err.println("Nie znalezniono projektu!");
        }
    }
}
