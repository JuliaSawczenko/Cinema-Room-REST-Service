package cinema;

import cinema.models.SeatDTO;
import cinema.models.Token;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class Ticket {
    @JsonUnwrapped
    private Token token;
    @JsonProperty("ticket")
    private SeatDTO seat;

    public Ticket(Token token, SeatDTO seat) {
        this.token = token;
        this.seat = seat;
    }

    public Token getToken() {
        return token;
    }

    public SeatDTO getSeat() {
        return seat;
    }

    public void setToken(Token token) {
        this.token = token;
    }



}
