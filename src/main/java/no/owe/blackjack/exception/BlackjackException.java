package no.owe.blackjack.exception;

public class BlackjackException extends RuntimeException {
    private static final long serialVersionUID = 7728828512142293558L;

    public BlackjackException(String message) {
        super(message);
    }

    public BlackjackException(String message, Throwable cause) {
        super(message, cause);
    }
}
