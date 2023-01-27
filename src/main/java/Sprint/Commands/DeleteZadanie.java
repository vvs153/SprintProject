package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Sprint;
import Sprint.model.Zadanie;

public class DeleteZadanie implements Command{
    private DataAccessObject<Zadanie> dao = new DataAccessObject<>();
    @Override
    public String getCommand() {
        return "Usun zadanie";
    }

    @Override
    public void service() {
        System.out.println("Podaj id usuwanego zadania:");
        String id = scanner.nextLine();
        Long zadanieId = Long.parseLong(id);
        if(dao.delete(Zadanie.class,zadanieId)){
            System.out.println("Usunieto zadanie!");
        } else {
            System.err.println("Nie znalezniono zadania!");
        }
    }
}
