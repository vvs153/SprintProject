package Sprint.Commands;

import Sprint.Database.HibernateUtil;
import Sprint.model.Zadanie;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.List;

public class FindZadanieName implements Command{
    @Override
    public String getCommand() {
        return "Znajdz zadania po nazwie";
    }

    @Override
    public void service() {
        System.out.println("Podaj nazwe zadania");
        String zadanieName = scanner.nextLine();
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            TypedQuery<Zadanie> zapytanie = session.createQuery("FROM Zadanie", Zadanie.class);
          //  zapytanie.setParameter("i", productId);
            List<Zadanie> lista = zapytanie.getResultList();
            for (Zadanie zadanie : lista) {
                if(zadanie.getNazwa().equalsIgnoreCase(zadanieName)) {
                    System.out.println(zadanie);
                }
                }

            }
        }
    }

