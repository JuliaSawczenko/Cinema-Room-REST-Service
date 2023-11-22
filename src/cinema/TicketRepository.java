package cinema;

import cinema.exception.WrongInputException;
import cinema.models.SeatDTO;
import cinema.models.SeatsDTO;
import cinema.models.StatsDTO;
import cinema.models.Token;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("singleton")
public class TicketRepository {

    private Cinema cinema;
    Map<String, Ticket> purchasedTickets;
    private int income;

    private int purchased;

    public TicketRepository() {
        this.cinema = new Cinema();
        purchasedTickets = new HashMap();
    }

    public SeatsDTO getSeats() {
        return new SeatsDTO(cinema.getRows(), cinema.getColumns(), getAvailableSeats());
    }

    public ArrayList<SeatDTO> getAvailableSeats() {
        ArrayList<SeatDTO> availableSeats = new ArrayList<>(cinema.getListOfSeats().size());
        cinema.getListOfSeats().keySet().stream().sorted().forEach(key -> {
            if (!cinema.getListOfSeats().get(key).isPurchased())
                availableSeats.add(
                        new SeatDTO(cinema.getListOfSeats().get(key).getRow(),
                                cinema.getListOfSeats().get(key).getColumn(),
                                cinema.getListOfSeats().get(key).getPrice())
                );
        });
        return availableSeats;
    }



    public Ticket purchaseTicket(SeatDTO chosenSeat) {
        int key = chosenSeat.hashCode();

        if (!cinema.getListOfSeats().containsKey(key)) {
            throw new WrongInputException("The number of a row or a column is out of bounds!");
        } else {
            if (!cinema.getListOfSeats().get(key).isPurchased()) {
                cinema.getListOfSeats().get(key).setPurchased(true);
                this.purchased++;
                this.income = this.income + cinema.getListOfSeats().get(key).getPrice();
                Ticket ticket = new Ticket(new Token(), new SeatDTO(cinema.getListOfSeats().get(key).getRow(),
                        cinema.getListOfSeats().get(key).getColumn(),
                        cinema.getListOfSeats().get(key).getPrice()));
                purchasedTickets.put(ticket.getToken().getValue(), ticket);
                return ticket;

            } else {
                throw new WrongInputException("The ticket has been already purchased!");
            }
        }

    }

    public ReturnedTicket returnTicket(Token token) {
        if (!purchasedTickets.containsKey(token.getValue())) {
            throw new WrongInputException("Wrong token!");
        } else {
            ReturnedTicket returnedTicket = new ReturnedTicket(purchasedTickets.get(token.getValue()).getSeat());
            int key = returnedTicket.getSeat().hashCode();
            cinema.getListOfSeats().get(key).setPurchased(false);
            this.purchased--;
            this.income = this.income - cinema.getListOfSeats().get(key).getPrice();
            purchasedTickets.remove(token.getValue());
            return returnedTicket;
        }
    }

    public StatsDTO getStats() {
        return new StatsDTO(income, getAvailableSeats().size(), purchased);
    }

    public int getIncome() {
        return income;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }

    public Cinema getCinema() {
        return this.cinema;
    }
}
