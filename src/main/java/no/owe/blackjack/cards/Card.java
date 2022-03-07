package no.owe.blackjack.cards;

import no.owe.blackjack.util.CardUtil;

public class Card {
    public final String suit;
    public final String value;
    public final int integerValue;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
        this.integerValue = CardUtil.cardStringValueToIntValue(value);
    }

    @Override
    public String toString() {
        return this.suit + this.value;
    }
}
