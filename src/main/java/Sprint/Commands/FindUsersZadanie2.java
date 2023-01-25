package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.Database.HibernateUtil;
import Sprint.model.Project;
import Sprint.model.Sprint;
import Sprint.model.User;
import Sprint.model.Zadanie;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FindUsersZadanie2 implements Command{
    private DataAccessObject<User> daoUser = new DataAccessObject<>();
    @Override
    public String getCommand() {
        return "2";
    }

    @Override
    public void service() {
        System.out.println("Podaj id uzytkownika");
        String id = scanner.nextLine();
        Long userId = Long.parseLong(id);
        Optional<User> optionalUser = daoUser.find(User.class, userId);
        if(optionalUser.isEmpty()){
            System.err.println("Id uzytkownika nie istnieje!");
            return;
        }
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            User user = session.get(User.class, userId);
            Set<Zadanie> zadania = user.getZadanieSet();
            System.out.println("Lista zadan uzytkownika o id: " + id);
            zadania.forEach(System.out::println);
            }

      /*  Project p = new Project() ;
        List<Sprint> sprinty =p.getZadanie2Set().stream()
                .flatMap(zadanie -> zadanie.getSprintSet().stream())
                .distinct()
                .collect(Collectors.toList());*/
        }
    }

