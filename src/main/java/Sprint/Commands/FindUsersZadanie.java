package Sprint.Commands;

import Sprint.Database.HibernateUtil;
import Sprint.model.Project;
import Sprint.model.Sprint;
import Sprint.model.Zadanie;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class FindUsersZadanie implements Command{
    @Override
    public String getCommand() {
        return "Znajdz zadania uzytkownika";
    }

    @Override
    public void service() {
        System.out.println("Podaj id uzytkownika");
        String id = scanner.nextLine();
        Long userId = Long.parseLong(id);
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            TypedQuery<Zadanie> zapytanie = session.createQuery("FROM Zadanie z WHERE z.user_id=" + userId, Zadanie.class);
          //  zapytanie.setParameter("i", productId);
            List<Zadanie> lista = zapytanie.getResultList();
            System.out.println("Zadania uzytkownika o id "+userId +":");
            for (Zadanie zadanie : lista) {
                if(zadanie.getProject().getId()==userId) {
                    System.out.println(zadanie);
                }
                }

            }

        Project p = new Project() ;
        List<Sprint> sprinty =p.getZadanie2Set().stream()
                .flatMap(zadanie -> zadanie.getSprintSet().stream())
                .distinct()
                .collect(Collectors.toList());
        }
    }

