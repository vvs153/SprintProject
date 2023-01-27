package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Sprint;
import Sprint.model.User;

public class DeleteUser implements Command{
    private DataAccessObject<User> dao = new DataAccessObject<>();
    @Override
    public String getCommand() {
        return "Usun uzytkownika";
    }

    @Override
    public void service() {
        System.out.println("Podaj id usuwanego uzytkownika:");
        String id = scanner.nextLine();
        Long userId = Long.parseLong(id);
        if(dao.delete(User.class,userId)){
            System.out.println("Usunieto uzytkownika!");
        } else {
            System.err.println("Nie znalezniono uzytkownika!");
        }
    }
}
