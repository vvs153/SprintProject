package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Project;
import Sprint.model.Zadanie;

import java.util.List;

public class ListZadanie implements Command {
    private DataAccessObject<Zadanie> dao;
    public ListZadanie(){
        this.dao = new DataAccessObject<>();
    }
    @Override
    public String getCommand() {
        return "Lista zadan";
    }

    @Override
    public void service() {
        List<Zadanie> zadania = dao.findAll(Zadanie.class);
        zadania.forEach(System.out::println);
    }
}
