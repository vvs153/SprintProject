package Sprint.Commands;

import Sprint.Database.HibernateUtil;
import Sprint.model.Project;
import Sprint.model.Sprint;
import Sprint.model.User;
import Sprint.model.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeletePST implements Command {

    @Override
    public String getCommand() {
        return "Usun projekt wraz z relacjami";
    }

    @Override
    public void service() {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            System.out.println("Podaj id projektu:");
            Long id = scanner.nextLong();
            Project project = session.get(Project.class, id);
            if (project == null) {
                System.out.println("Projekt o podanym id nie istnieje!!");
                return;
            }
            if(!project.getUsers().isEmpty()){
                if(!project.getTask2Set().isEmpty()){
                    if(!project.getTask2Set().stream().flatMap(zadanie -> zadanie.getSprintSet().stream()).toList().isEmpty()){
                        for (Sprint sprint: project.getTask2Set().stream().flatMap(zadanie -> zadanie.getSprintSet().stream()).toList()) {
                            session.remove(sprint);
                        }
                    }
                    for (Task task : project.getTask2Set()){
                        session.remove(task);
                    }
                } for (User user: project.getUsers()){
                    session.remove(user);
                }
            } session.remove(project);
            transaction.commit();
            System.out.println("Usunieto projekt z powiązanymi zadaniami, userami i sprintami!!!");
        } catch (Exception ioe) {
            System.err.println("blad bazy: " + ioe);

        }
    }
}
