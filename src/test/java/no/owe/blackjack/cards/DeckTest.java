package no.owe.blackjack.cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeckTest {
    @Test
    public void newDeckShouldCreateCorrectDeckSize() {
        int correctDeckSize = Deck.SUITS.size() * Deck.VALUES.size();
        assertEquals(correctDeckSize, new Deck().size());
    }
}