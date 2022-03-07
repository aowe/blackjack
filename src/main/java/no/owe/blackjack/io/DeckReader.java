package no.owe.blackjack.io;

import no.owe.blackjack.cards.Card;
import no.owe.blackjack.cards.Deck;
import no.owe.blackjack.exception.BlackjackException;
import no.owe.blackjack.util.CardUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeckReader {

    /**
     * Creates a new deck of cards from a file.
     * The deck must be on the first line in the following format:
     * CA, D4, H7, SJ,..., S5, S9, D10
     * Subsequent lines are ignored.
     *
     * @param file
     * @return New deck of cards
     */
    public Deck readDeck(final String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            reader.close();

            return new Deck(new ArrayDeque<>(createDeckFromLine(line)));
        } catch (IOException e) {
            throw new BlackjackException("Error opening file", e);
        }
    }

    /**
     * Creates a new deck of cards from a String with the following format:
     * CA, D4, H7, SJ,..., S5, S9, D10
     *
     * @param line
     * @return List of cards
     */
    public List<Card> createDeckFromLine(final String line) {
        if (line == null || line.isEmpty()) {
            throw new BlackjackException("Input line is empty");
        }

        return Arrays.stream(line.split(","))
                .map(String::trim)
                .map(this::extractCardFromString)
                .collect(Collectors.toList());
    }

    private Card extractCardFromString(final String card) {
        String suit;
        String value;
        try {
            suit = card.substring(0, 1);
            value = card.substring(1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new BlackjackException("Empty card in input", e);
        }

        if (!Deck.SUITS.contains(suit)) {
            throw new BlackjackException("Card has invalid suit: " + suit);
        }

        return new Card(suit, value);
    }
}
