package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Project;
import Sprint.model.User;
import Sprint.model.Zadanie;

import java.util.Optional;
import java.util.regex.Pattern;

public class UpdateUser implements Command{
    private DataAccessObject<Project> daoProject = new DataAccessObject<>();
    private DataAccessObject<User> daoUser = new DataAccessObject<>();
    private DataAccessObject<Zadanie> daoZadanie = new DataAccessObject<>();

    public UpdateUser() {
        this.daoProject = daoProject;
        this.daoUser = daoUser;
        this.daoZadanie = daoZadanie;
    }

    @Override
    public String getCommand() {
        return "Aktualizuj uzytkownika";
    }

    @Override
    public void service() {

        System.out.println("Podaj id uzytkownika:");
        String idUserString = Command.scanner.nextLine();
        Long idUser = Long.parseLong(idUserString);
        Optional<User> userOptional = daoUser.find(User.class, idUser);
        if (userOptional.isEmpty()) {
            System.err.println("Uzytkownik o podanym id nie istnieje!");
            return;
        }
        System.out.println("Podaj adres email:");
        String email = Command.scanner.nextLine();
        boolean w = Pattern.compile("^(.+)@(.+)$").matcher(email).find();
        if (w){
            System.out.println("Podaj imie uzytkownika:");
            String name = Command.scanner.nextLine();
            System.out.println("Podaj nazwisko uzytkownika:");
            String surname = Command.scanner.nextLine();
            System.out.println("Podaj nick:");
            String nick = Command.scanner.nextLine();

            User user = User.builder()
                    .id(idUser)
                    .imie(name)
                    .nazwisko(surname)
                    .email(email)
                    .nick(nick)
                    .projects(userOptional.get().getProjects())
                    .build();
            daoUser.update(User.class,idUser,user);
        } else {
            System.out.println("Podano niewlasciwy adres email!");
        }

    }
}
