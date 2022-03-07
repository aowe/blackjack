package no.owe.blackjack.util;

import no.owe.blackjack.cards.Card;
import no.owe.blackjack.exception.BlackjackException;
import no.owe.blackjack.io.DeckReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardUtilTest {

    @Test
    void aceReturnsEleven() {
        assertEquals(11, CardUtil.cardStringValueToIntValue("A"));
    }

    @Test
    void kingReturnsTen() {
        assertEquals(10, CardUtil.cardStringValueToIntValue("K"));
    }

    @Test
    void queenReturnsTen() {
        assertEquals(10, CardUtil.cardStringValueToIntValue("Q"));
    }

    @Test
    void jackReturnsTen() {
        assertEquals(10, CardUtil.cardStringValueToIntValue("J"));
    }

    @Test
    void integerValueParsesCorrectly() {
        assertEquals(8, CardUtil.cardStringValueToIntValue("8"));
    }

    @Test
    void invalidValueThrowsException() {
        assertThrows(BlackjackException.class, () -> {
            CardUtil.cardStringValueToIntValue("F");
        }, "Should throw exception when value is invalid");
    }

    @Test
    void missingValueThrowsException() {
        assertThrows(BlackjackException.class, () -> {
            CardUtil.cardStringValueToIntValue("");
        }, "Should throw exception when value is invalid");
    }


    @Test
    void determineHandScore() {
        List<Card> cardList = new DeckReader().createDeckFromLine("H2, H6, H9");
        assertEquals(17, CardUtil.determineHandScore(cardList));
    }

    @Test
    void hasBlackjackReturnsTrue() {
        List<Card> cardList = new DeckReader().createDeckFromLine("C2, CA, C8");
        assertTrue(CardUtil.hasBlackjack(cardList));
    }

    @Test
    void hasBlackjackReturnsFalse() {
        List<Card> cardList = new DeckReader().createDeckFromLine("H2, H6, H9");
        assertFalse(CardUtil.hasBlackjack(cardList));
    }
}