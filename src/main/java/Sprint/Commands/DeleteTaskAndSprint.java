package Sprint.Commands;

import Sprint.Database.HibernateUtil;
import Sprint.model.Sprint;
import Sprint.model.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteTaskAndSprint implements Command {

    @Override
    public String getCommand() {
        return "Usun zadanie i sprinty";
    }

    @Override
    public void service() {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            System.out.println("Podaj id zadania:");
            Long id = scanner.nextLong();
            Task task = session.get(Task.class, id);
            if (task == null) {
                System.out.println("Zadanie o podanym id nie istnieje!");
                return;
            }
            if(!task.getSprintSet().isEmpty()){
                    for (Sprint sprint: task.getSprintSet()) {
                        session.remove(sprint);
                    }
                } session.remove(task);
                transaction.commit();
            System.out.println("Usunieto zadania wraz z sprintami!");

        } catch (Exception ioe) {
            System.err.println("blad bazy: " + ioe);

        }
    }
}
