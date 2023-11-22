package cinema.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SeatsDTO(@JsonProperty("rows")int total_rows,@JsonProperty("columns")int total_columns,@JsonProperty("seats")List<SeatDTO> available_seats) {

}
