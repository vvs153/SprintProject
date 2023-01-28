package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Task;

import java.util.List;

public class FindTaskByName implements Command{
    private DataAccessObject<Task> dao;

    public FindTaskByName() {
        this.dao = new DataAccessObject<>();;
    }

    @Override
    public String getCommand() {
        return "Znajdz zadania po nazwie";
    }

    @Override
    public void service() {
        System.out.println("Podaj nazwe zadania:");
        String zadanieName = scanner.nextLine();
        List<Task> zadania = dao.findAll(Task.class);
        System.out.println("Zadania o nazwie: " + zadanieName);
        zadania.stream().filter(zadanie -> zadanie.getNazwa().equalsIgnoreCase(zadanieName)).forEach(System.out::println);
            }
        }


