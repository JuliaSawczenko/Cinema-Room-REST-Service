package cinema;

import cinema.exception.WrongInputException;
import cinema.exception.WrongPasswordException;
import cinema.models.SeatDTO;
import cinema.models.SeatsDTO;
import cinema.models.StatsDTO;
import cinema.models.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CinemaController {
    @Autowired TicketRepository ticketRepository;
    @GetMapping("/seats")
    public SeatsDTO getSeats() {
        return ticketRepository.getSeats();
    }

    @PostMapping("/purchase")
    public Ticket purchaseTicket(@Valid @RequestBody SeatDTO chosenSeat) {
        return ticketRepository.purchaseTicket(chosenSeat);
    }

    @PostMapping("/return")
    public ReturnedTicket returnTicket(@RequestBody Token token) {
       return ticketRepository.returnTicket(token);
    }

    @GetMapping("/stats")
    public StatsDTO getStats(@RequestParam (required = false) String password) {
         if ((password == null) || (!password.equals("super_secret"))) {
            throw new WrongPasswordException("The password is wrong!");
        } else {
            return ticketRepository.getStats();
        }
    }

    @ExceptionHandler(WrongInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleWrongInput(WrongInputException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", e.getMessage());
        return map;
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleWrongPassword(WrongPasswordException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", e.getMessage());
        return map;
    }
}

