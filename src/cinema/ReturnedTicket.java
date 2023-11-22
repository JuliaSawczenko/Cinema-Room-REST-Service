package cinema;

import cinema.models.SeatDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("ticket")
public class ReturnedTicket {


    private SeatDTO seat;

    public ReturnedTicket(SeatDTO seat) {
        this.seat = seat;
    }
    @JsonProperty("ticket")
    public SeatDTO getSeat() {
        return seat;
    }
}
