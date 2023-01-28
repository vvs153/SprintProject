package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Task;

public class DeleteTask implements Command{
    private DataAccessObject<Task> dao = new DataAccessObject<>();
    @Override
    public String getCommand() {
        return "Usun zadanie";
    }

    @Override
    public void service() {
        System.out.println("Podaj id usuwanego zadania:");
        String id = scanner.nextLine();
        Long zadanieId = Long.parseLong(id);
        if(dao.delete(Task.class,zadanieId)){
            System.out.println("Usunieto zadanie!");
        } else {
            System.err.println("Nie znalezniono zadania!");
        }
    }
}
