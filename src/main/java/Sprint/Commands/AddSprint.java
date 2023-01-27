package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Progress;
import Sprint.model.Sprint;
import Sprint.model.Zadanie;

import java.time.LocalDate;
import java.util.Optional;

public class AddSprint implements Command{
    private DataAccessObject<Zadanie> dao = new DataAccessObject<>();
    private DataAccessObject<Sprint> daoSprint = new DataAccessObject<>();

    public AddSprint() {
        this.dao = dao;
        this.daoSprint = daoSprint;
    }

    @Override
    public String getCommand() {
        return "Dodaj sprint";
    }

    @Override
    public void service() {
        System.out.println("Podaj id zadania");
        String idString = Command.scanner.nextLine();
        Long idZadanie = Long.parseLong(idString);

        Optional<Zadanie> optionalZadanie = dao.find(Zadanie.class, idZadanie);
        if(optionalZadanie.isEmpty()){
            System.err.println("Zadanie o podanym id nie istnieje!");
            return;
        }
        System.out.println("Podaj poczatek sprintu (YYYY-MM-DD):");
        String stringStartDate = Command.scanner.nextLine();
        LocalDate startDate  = LocalDate.parse(stringStartDate);
        System.out.println("Podaj koniec sprintu (YYYY-MM-DD):");
        String stringEndDate = Command.scanner.nextLine();
        LocalDate endDate  = LocalDate.parse(stringEndDate);
        if(endDate.isBefore(startDate)){
            System.err.println("Data zakonczenia jest wczesniejsza od daty startu!");
            return;
        }
        System.out.println("Podaj punkty sprintu:");
        String pointsString = Command.scanner.nextLine();
        int points = Integer.parseInt(pointsString);

        Sprint sprint = Sprint.builder()
                .zadanie(optionalZadanie.get())
                .poczatek(startDate)
                .koniec(endDate)
                .sprintPoints(points)
                .build();

        daoSprint.insert(sprint);
    }
}
