package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.Database.HibernateUtil;
import Sprint.model.Sprint;
import Sprint.model.Zadanie;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteZS implements Command {

    @Override
    public String getCommand() {
        return "Usun zadanie i sprinty";
    }

    @Override
    public void service() {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            System.out.println("Podaj id zadania");
            Long id = scanner.nextLong();
            Zadanie zadanie = session.get(Zadanie.class, id);
            if (zadanie == null) {
                System.out.println("Nie ma takiego zadania!");
                return;
            }
            if(!zadanie.getSprintSet().isEmpty()){
                    for (Sprint sprint: zadanie.getSprintSet()) {
                        session.remove(sprint);
                    }
                } session.remove(zadanie);
                transaction.commit();
            System.out.println("Usunieto zadania wraz z sprintami!");

        } catch (Exception ioe) {
            System.err.println("blad bazy: " + ioe);

        }
    }
}
