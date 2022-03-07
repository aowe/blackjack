package no.owe.blackjack.logic;

import no.owe.blackjack.cards.Card;
import no.owe.blackjack.cards.Deck;
import no.owe.blackjack.exception.BlackjackException;
import no.owe.blackjack.io.DeckReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static no.owe.blackjack.util.CardUtil.determineHandScore;
import static no.owe.blackjack.util.CardUtil.hasBlackjack;

public class BlackjackImpl {
    private static final int DEALER = 1;
    private static final int SAM = 0;

    private final DeckReader deckReader;

    public BlackjackImpl() {
        this.deckReader = new DeckReader();
    }

    public int play() {
        return play(new Deck());
    }

    public int play(final String file) {
        Deck deck = deckReader.readDeck(file);
        return play(deck);
    }

    // Output is for use by unit-tests
    public int play(final Deck deck) {
        final List<Card> samHand = new ArrayList<>();
        final List<Card> dealerHand = new ArrayList<>();

        //Deal out initial hands
        samHand.add(deck.pop());
        dealerHand.add(deck.pop());
        samHand.add(deck.pop());
        dealerHand.add(deck.pop());

        Optional<Integer> initialWinner = checkIfWinnerExistsAfterInitialHand(samHand, dealerHand);
        if (initialWinner.isPresent()) {
            return initialWinner.get();
        }

        while (determineHandScore(samHand) < 17) {
            samHand.add(deck.pop());
        }

        if (determineHandScore(samHand) > 21) {
            printWinnerToStdout(DEALER, samHand, dealerHand);
            return DEALER;
        }

        while (determineHandScore(dealerHand) <= determineHandScore(samHand)) {
            dealerHand.add(deck.pop());
        }

        if (determineHandScore(dealerHand) > 21) {
            printWinnerToStdout(SAM, samHand, dealerHand);
            return SAM;
        }

        Optional<Integer> winner = determineWinner(samHand, dealerHand);
        if (winner.isPresent()) {
            return winner.get();
        }

        throw new BlackjackException("Invalid game state! No winner declared");
    }

    private Optional<Integer> determineWinner(List<Card> sam, List<Card> dealer) {
        if (determineHandScore(sam) > determineHandScore(dealer)) {
            printWinnerToStdout(SAM, sam, dealer);
            return Optional.of(SAM);
        }

        if (determineHandScore(sam) < determineHandScore(dealer)) {
            printWinnerToStdout(DEALER, sam, dealer);
            return Optional.of(DEALER);
        }

        return Optional.empty();
    }

    private Optional<Integer> checkIfWinnerExistsAfterInitialHand(List<Card> sam, List<Card> dealer) {
        if (hasBlackjack(sam)) {
            printWinnerToStdout(SAM, sam, dealer);
            return Optional.of(SAM);
        }

        if (hasBlackjack(dealer)) {
            printWinnerToStdout(DEALER, sam, dealer);
            return Optional.of(DEALER);
        }

        if (determineHandScore(sam) == 22 && determineHandScore(dealer) == 22) {
            printWinnerToStdout(DEALER, sam, dealer);
            return Optional.of(DEALER);
        }
        return Optional.empty();
    }

    private void printWinnerToStdout(final int winner, final List<Card> sam, final List<Card> dealer) {
        System.out.println(winner == SAM ? "sam" : "dealer");
        System.out.println("sam: " + sam.stream().map(Card::toString).collect(Collectors.joining(", ")));
        System.out.println("dealer: " + dealer.stream().map(Card::toString).collect(Collectors.joining(", ")));
    }
}
