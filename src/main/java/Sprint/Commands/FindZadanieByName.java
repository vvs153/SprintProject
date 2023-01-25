package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Zadanie;

import java.util.List;

public class FindZadanieByName implements Command{
    private DataAccessObject<Zadanie> dao;

    public FindZadanieByName() {
        this.dao = new DataAccessObject<>();;
    }

    @Override
    public String getCommand() {
        return "Znajdz zadania po nazwie";
    }

    @Override
    public void service() {
        System.out.println("Podaj nazwe zadania");
        String zadanieName = scanner.nextLine();
        List<Zadanie> zadania = dao.findAll(Zadanie.class);
        System.out.println("Zadania o nazwie: " + zadanieName);
        zadania.stream().filter(zadanie -> zadanie.getNazwa().equalsIgnoreCase(zadanieName)).forEach(System.out::println);
            }
        }


