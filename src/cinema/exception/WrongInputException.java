package cinema.exception;

public class WrongInputException extends RuntimeException {

    public WrongInputException(String error) {
        super(error);
    }
}
