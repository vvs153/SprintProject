package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.Database.HibernateUtil;
import Sprint.model.Zadanie;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.List;

public class FindZadanieName2 implements Command{
    private DataAccessObject<Zadanie> dao;

    public FindZadanieName2() {
        this.dao = new DataAccessObject<>();;
    }

    @Override
    public String getCommand() {
        return "3";
    }

    @Override
    public void service() {
        System.out.println("Podaj nazwe zadania");
        String zadanieName = scanner.nextLine();
        List<Zadanie> zadania = dao.findAll(Zadanie.class);
        zadania.stream().filter(zadanie -> zadanie.getNazwa().equalsIgnoreCase(zadanieName)).forEach(System.out::println);
            }
        }


