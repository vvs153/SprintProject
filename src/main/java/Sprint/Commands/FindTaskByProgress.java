package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Task;

import java.util.List;

public class FindTaskByProgress implements Command{
    private DataAccessObject<Task> dao;

    public FindTaskByProgress() {
        this.dao = new DataAccessObject<>();;
    }

    @Override
    public String getCommand() {
        return "Znajdz zadania po statusie";
    }

    @Override
    public void service() {
        System.out.println("Podaj status zadania:");
        String zadanieStatus = scanner.nextLine();
        List<Task> zadania = dao.findAll(Task.class);
        System.out.println("Zadania o statusie: " + zadanieStatus);
        zadania.stream().filter(zadanie -> zadanie.getProgress().toString().equalsIgnoreCase(zadanieStatus)).forEach(System.out::println);
            }
        }


