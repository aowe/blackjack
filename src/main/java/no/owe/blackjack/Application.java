package no.owe.blackjack;

import no.owe.blackjack.exception.BlackjackException;
import no.owe.blackjack.logic.BlackjackImpl;

public class Application {
    public static void main(String[] args) {
        BlackjackImpl blackjack = new BlackjackImpl();

        try {
            if (args.length > 0) {
                blackjack.play(args[0]);
            } else {
                blackjack.play();
            }
        } catch (BlackjackException e) {
            System.out.println(e.getMessage());
        }
    }
}
