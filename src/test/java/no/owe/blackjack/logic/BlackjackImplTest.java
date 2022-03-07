package no.owe.blackjack.logic;

import no.owe.blackjack.cards.Card;
import no.owe.blackjack.cards.Deck;
import no.owe.blackjack.exception.BlackjackException;
import no.owe.blackjack.io.DeckReader;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BlackjackImplTest {

    BlackjackImpl blackjack = new BlackjackImpl();
    DeckReader deckReader = new DeckReader();

    @Test
    void samHasInitialBlackjackAndWins() {
        List<Card> cardList = deckReader.createDeckFromLine("CA, D5, HK, HQ, S8");
        Deck deck = new Deck(new ArrayDeque<>(cardList));
        int outcome = blackjack.play(deck);

        assertEquals(0, outcome, "Sam wins");
    }

    @Test
    void dealerHasInitialBlackjackAndWins() {
        List<Card> cardList = deckReader.createDeckFromLine("C2, DA, HK, HQ, S8");
        Deck deck = new Deck(new ArrayDeque<>(cardList));
        int outcome = blackjack.play(deck);

        assertEquals(1, outcome, "Dealer wins");
    }

    @Test
    void dealerWinsWithEqualInitialScoreOfTwentyTwo() {
        List<Card> cardList = deckReader.createDeckFromLine("HA, DA, SA, CA");
        Deck deck = new Deck(new ArrayDeque<>(cardList));
        int outcome = blackjack.play(deck);

        assertEquals(1, outcome, "Dealer wins");
    }

    @Test
    void samLosesAfterDrawingCardsWithValueOverTwentyOne() {
        List<Card> cardList = deckReader.createDeckFromLine("H2, C3, D7, C7, H7, HA");
        Deck deck = new Deck(new ArrayDeque<>(cardList));
        int outcome = blackjack.play(deck);

        assertEquals(1, outcome, "Dealer wins");
    }

    @Test
    void samWinsAfterDealerDrawsCardsWithValueOverTwentyOne() {
        List<Card> cardList = deckReader.createDeckFromLine("H2, C3, D7, C7, H10, H8, HA");
        Deck deck = new Deck(new ArrayDeque<>(cardList));
        int outcome = blackjack.play(deck);

        assertEquals(0, outcome, "Sam wins");
    }

    @Test
    void samWinsAfterGettingBlackjackAndDealerDrawingValueOverTwentyOne() {
        List<Card> cardList = deckReader.createDeckFromLine("H3, C3, D7, C7, CA, HA, H2");
        Deck deck = new Deck(new ArrayDeque<>(cardList));
        int outcome = blackjack.play(deck);

        assertEquals(0, outcome, "Sam wins");
    }

    @Test
    void dealerWinsWithHighestScore() {
        List<Card> cardList = deckReader.createDeckFromLine("H2, C3, D7, C7, H10, H8, S2");
        Deck deck = new Deck(new ArrayDeque<>(cardList));
        int outcome = blackjack.play(deck);

        assertEquals(1, outcome, "Dealer wins");
    }

    @Test
    void notEnoughCardsInDeckToWinCausesThrownException() {
        List<Card> cardList = deckReader.createDeckFromLine("H2, C3, D7, C7");
        Deck deck = new Deck(new ArrayDeque<>(cardList));
        assertThrows(BlackjackException.class, () -> {
            blackjack.play(deck);
        });
    }
}