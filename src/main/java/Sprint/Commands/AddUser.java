package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.User;

import java.util.regex.Pattern;

public class AddUser implements Command{
    private DataAccessObject<User> dao = new DataAccessObject<>();

    public AddUser() {
        this.dao = dao;
    }

    @Override
    public String getCommand() {
        return "Dodaj uzytkownika";
    }

    @Override
    public void service() {
        System.out.println("Podaj adres email:");
        String email = Command.scanner.nextLine();
        boolean w = Pattern.compile("[^\s+].@.").matcher(email).find();
        System.out.println(w);
        if (w){
            System.out.println("Podaj imie uzytkownika:");
            String name = Command.scanner.nextLine();
            System.out.println("Podaj nazwisko uzytkownika:");
            String surname = Command.scanner.nextLine();

            System.out.println("Podaj nick:");
            String nick = Command.scanner.nextLine();

            User user = User.builder()
                    .imie(name)
                    .nazwisko(surname)
                    .email(email)
                    .nick(nick)
                    .build();
            dao.insert(user);
        } else {
            System.out.println("Podano niewlasciwy adres email");
        }

    }
}
