package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Zadanie;

import java.util.List;

public class FindZadanieByProgress implements Command{
    private DataAccessObject<Zadanie> dao;

    public FindZadanieByProgress() {
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
        List<Zadanie> zadania = dao.findAll(Zadanie.class);
        System.out.println("Zadania o statusie: " + zadanieStatus);
        zadania.stream().filter(zadanie -> zadanie.getProgress().toString().equalsIgnoreCase(zadanieStatus)).forEach(System.out::println);
            }
        }


