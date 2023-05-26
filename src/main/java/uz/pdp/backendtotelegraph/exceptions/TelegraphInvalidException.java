package uz.pdp.backendtotelegraph.exceptions;

public class TelegraphInvalidException extends RuntimeException {
    public TelegraphInvalidException(String message) {
        super(message);
    }
}
