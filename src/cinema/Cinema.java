package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.concurrent.ConcurrentHashMap;

public class Cinema {
    @JsonProperty("rows")
    private int total_rows;
    @JsonProperty("columns")
    private int total_columns;
    @JsonIgnore
    private int total_seats;
    @JsonProperty("seats")
    private ConcurrentHashMap<Integer, Seat> listOfSeats;

    public Cinema() {
        this.total_rows = 9;
        this.total_columns = 9;
        this.total_seats = total_rows * total_columns;
        this.listOfSeats = createSeats();
        }

        private ConcurrentHashMap<Integer, Seat> createSeats() {
            ConcurrentHashMap<Integer, Seat> seats = new ConcurrentHashMap<>();
            for (int i = 1; i <= total_rows; i++) {
                for (int j = 1; j <= total_columns; j++) {
                    seats.put(i * 100 + j, new Seat(i, j));
                }
            }
            return seats;
        }


    public int getRows() {
        return total_rows;
    }

    public int getColumns() {
        return total_columns;
    }

    public ConcurrentHashMap<Integer, Seat> getListOfSeats() {
        return listOfSeats;
    }


}
