package cinema.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Token {
    @JsonProperty("token")
    private String value;

    public Token() {
        this.value = String.valueOf(UUID.randomUUID());
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
