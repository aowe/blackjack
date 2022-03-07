package no.owe.blackjack.io;

import no.owe.blackjack.cards.Card;
import no.owe.blackjack.exception.BlackjackException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeckReaderTest {

    @Test
    void createDeckFromLine() {
        DeckReader reader = new DeckReader();
        List<Card> cardList = reader.createDeckFromLine("CA, D5, HJ");

        assertEquals(11, cardList.get(0).integerValue);
        assertEquals("C", cardList.get(0).suit);
        assertEquals("A", cardList.get(0).value);

        assertEquals(5, cardList.get(1).integerValue);
        assertEquals("D", cardList.get(1).suit);
        assertEquals("5", cardList.get(1).value);

        assertEquals(10, cardList.get(2).integerValue);
        assertEquals("H", cardList.get(2).suit);
        assertEquals("J", cardList.get(2).value);
    }

    @Test
    void createDeckFromEmptyLineThrowsException() {
        DeckReader reader = new DeckReader();

        assertThrows(BlackjackException.class, () -> {
            reader.createDeckFromLine("");
        }, "Empty line should throw exception");
    }

    @Test
    void createDeckFromLineInvalidValueThrowsException() {
        DeckReader reader = new DeckReader();

        assertThrows(BlackjackException.class, () -> {
            reader.createDeckFromLine("C1");
        }, "Invalid value should throw exception");
    }

    @Test
    void createDeckFromLineInvalidSuitThrowsException() {
        DeckReader reader = new DeckReader();

        assertThrows(BlackjackException.class, () -> {
            reader.createDeckFromLine("T6");
        }, "Invalid suit should throw exception");
    }

    @Test
    void createDeckFromLineEmptyCardThrowsException() {
        DeckReader reader = new DeckReader();

        assertThrows(BlackjackException.class, () -> {
            reader.createDeckFromLine("C6,,C5");
        }, "Empty card should throw exception");
    }

}