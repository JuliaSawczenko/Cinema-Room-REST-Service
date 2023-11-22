package cinema.exception;

public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException(String error) {
        super(error);
    }
}
