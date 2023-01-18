package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Sprint;

public class DeleteSprint implements Command{
    private DataAccessObject<Sprint> dao = new DataAccessObject<>();
    @Override
    public String getCommand() {
        return "Usun sprint";
    }

    @Override
    public void service() {
        System.out.println("Podaj id usuwanego sprintu");
        String id = scanner.nextLine();
        Long sprintId = Long.parseLong(id);
        if(dao.delete(Sprint.class,sprintId)){
            System.out.println("Usunieto sprint");
        } else {
            System.err.println("Nie znalezniono sprintu");
        }
    }
}
