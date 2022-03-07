package no.owe.blackjack.util;

import no.owe.blackjack.cards.Card;
import no.owe.blackjack.cards.Deck;
import no.owe.blackjack.exception.BlackjackException;

import java.util.List;

public class CardUtil {
    private CardUtil() {
    }

    public static int cardStringValueToIntValue(final String value) {
        if (value == null || value.isEmpty()) {
            throw new BlackjackException("Card has no value");
        }

        if (!Deck.VALUES.contains(value)) {
            throw new BlackjackException("Card has invalid value: " + value);
        }

        switch (value) {
            case "J":
            case "Q":
            case "K":
                return 10;
            case "A":
                return 11;
            default:
                return Integer.parseInt(value);
        }
    }

    public static int determineHandScore(final List<Card> hand) {
        return hand.stream()
                .map(card -> card.integerValue)
                .reduce(0, Integer::sum);
    }

    public static boolean hasBlackjack(final List<Card> hand) {
        return determineHandScore(hand) == 21;
    }
}