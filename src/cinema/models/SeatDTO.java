package cinema.models;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public record SeatDTO (@JsonProperty("row") @Min(1) @Max(9) int row, @JsonProperty("column") @Min(1) @Max(9) int column, @JsonProperty("price")int price) {

    @Override
    public int hashCode() {
        return row * 100 + column;
    }
}
