package Sprint.Commands;

import Sprint.Database.DataAccessObject;
import Sprint.model.Sprint;
import Sprint.model.Zadanie;

import java.time.LocalDate;
import java.util.Optional;

public class UpdateSprint implements Command{
    private DataAccessObject<Sprint> daoSprint = new DataAccessObject<>();
    private DataAccessObject<Zadanie> daoZadanie = new DataAccessObject<>();

    public UpdateSprint() {
        this.daoSprint = daoSprint;
        this.daoZadanie = daoZadanie;
    }

    @Override
    public String getCommand() {
        return "Aktualizuj sprint";
    }

    @Override
    public void service() {
        System.out.println("Podaj id sprintu");
        String idSprint = Command.scanner.nextLine();
        Long id = Long.parseLong(idSprint);
        if(!daoSprint.exists(Sprint.class,id)){
            System.err.println("Nie ma sprintu o takim id!");
            return;
        }
        System.out.println("Podaj id zadania");
        String idZadanieString = Command.scanner.nextLine();
        Long idZadanie = Long.parseLong(idZadanieString);
        Optional<Zadanie> zadanieOptional = daoZadanie.find(Zadanie.class, idZadanie);
        if (zadanieOptional.isEmpty()) {
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
                .id(id)
                .zadanie(zadanieOptional.get())
                .poczatek(startDate)
                .koniec(endDate)
                .sprintPoints(points)
                .build();
        daoSprint.update(Sprint.class,id,sprint);
    }
}
