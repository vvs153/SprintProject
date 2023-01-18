package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Project;
import Sprint.model.User;

import java.util.List;

public class ListUser implements Command {
    private DataAccessObject<User> dao;
    public ListUser(){
        this.dao = new DataAccessObject<>();
    }
    @Override
    public String getCommand() {
        return "Lista uzytkownikow";
    }

    @Override
    public void service() {
        List<User> users = dao.findAll(User.class);
        users.forEach(System.out::println);
    }
}
