package no.owe.blackjack.cards;

import no.owe.blackjack.exception.BlackjackException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Represents a deck of playing cards.
 * Possible SUITS and VALUES are defined in static lists
 * and are used to validate the creation of cards.
 */
public class Deck {
    public final static List<String> SUITS = Arrays.asList("D", "C", "S", "H");
    public final static List<String> VALUES = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");

    private final ArrayDeque<Card> cards;

    public Deck(ArrayDeque<Card> cards) {
        this.cards = cards;
    }

    /**
     * Initializes a new deck of 52 shuffled cards
     */
    public Deck() {
        List<Card> cardList = new ArrayList<>();

        for (String suit : SUITS) {
            for (String value : VALUES) {
                cardList.add(new Card(suit, value));
            }
        }

        Collections.shuffle(cardList);
        this.cards = new ArrayDeque<>(cardList);
    }

    public Card pop() {
        try {
            return this.cards.pop();
        } catch (NoSuchElementException e) {
            throw new BlackjackException("Deck is empty. Tried to draw too many cards.", e);
        }
    }

    int size() {
        return this.cards.size();
    }
}
