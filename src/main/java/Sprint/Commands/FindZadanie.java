package Sprint.Commands;

import Sprint.Database.HibernateUtil;
import Sprint.model.Project;
import Sprint.model.Zadanie;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.List;

public class FindZadanie implements Command{
    @Override
    public String getCommand() {
        return "Znajdz zadania w projekcie";
    }

    @Override
    public void service() {
        System.out.println("Podaj id projektu");
        String id = scanner.nextLine();
        Long projectId = Long.parseLong(id);
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            TypedQuery<Zadanie> zapytanie = session.createQuery("FROM Zadanie", Zadanie.class);
          //  zapytanie.setParameter("i", productId);
            List<Zadanie> lista = zapytanie.getResultList();
            System.out.println("Zadania w projekcie o id "+projectId +":");
            for (Zadanie zadanie : lista) {
                if(zadanie.getProject().getId()==projectId) {
                    System.out.println(zadanie);
                }
                }

            }
        }
    }

