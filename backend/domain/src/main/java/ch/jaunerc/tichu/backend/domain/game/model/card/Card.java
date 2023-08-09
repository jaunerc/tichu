package ch.jaunerc.tichu.backend.domain.game.model.card;

import static ch.jaunerc.tichu.backend.domain.game.model.card.CardRank.*;
import static ch.jaunerc.tichu.backend.domain.game.model.card.CardSuite.*;

public enum Card {

    DOG(CardRank.DOG, SPECIAL),
    MAHJONG(CardRank.MAHJONG, SPECIAL),
    PHOENIX(CardRank.PHOENIX, SPECIAL),
    DRAGON(CardRank.DRAGON, SPECIAL),

    TWO_JADE(TWO, JADE),
    THREE_JADE(THREE, JADE),
    FOUR_JADE(FOUR, JADE),
    FIVE_JADE(FIVE, JADE),
    SIX_JADE(SIX, JADE),
    SEVEN_JADE(SEVEN, JADE),
    EIGHT_JADE(EIGHT, JADE),
    NINE_JADE(NINE, JADE),
    TEN_JADE(TEN, JADE),
    JACK_JADE(JACK, JADE),
    QUEEN_JADE(QUEEN, JADE),
    KING_JADE(KING, JADE),
    ACE_JADE(ACE, JADE),

    TWO_SWORDS(TWO, SWORDS),
    THREE_SWORDS(THREE, SWORDS),
    FOUR_SWORDS(FOUR, SWORDS),
    FIVE_SWORDS(FIVE, SWORDS),
    SIX_SWORDS(SIX, SWORDS),
    SEVEN_SWORDS(SEVEN, SWORDS),
    EIGHT_SWORDS(EIGHT, SWORDS),
    NINE_SWORDS(NINE, SWORDS),
    TEN_SWORDS(TEN, SWORDS),
    JACK_SWORDS(JACK, SWORDS),
    QUEEN_SWORDS(QUEEN, SWORDS),
    KING_SWORDS(KING, SWORDS),
    ACE_SWORDS(ACE, SWORDS),

    TWO_PAGODAS(TWO, PAGODAS),
    THREE_PAGODAS(THREE, PAGODAS),
    FOUR_PAGODAS(FOUR, PAGODAS),
    FIVE_PAGODAS(FIVE, PAGODAS),
    SIX_PAGODAS(SIX, PAGODAS),
    SEVEN_PAGODAS(SEVEN, PAGODAS),
    EIGHT_PAGODAS(EIGHT, PAGODAS),
    NINE_PAGODAS(NINE, PAGODAS),
    TEN_PAGODAS(TEN, PAGODAS),
    JACK_PAGODAS(JACK, PAGODAS),
    QUEEN_PAGODAS(QUEEN, PAGODAS),
    KING_PAGODAS(KING, PAGODAS),
    ACE_PAGODAS(ACE, PAGODAS),

    TWO_STARS(TWO, STARS),
    THREE_STARS(THREE, STARS),
    FOUR_STARS(FOUR, STARS),
    FIVE_STARS(FIVE, STARS),
    SIX_STARS(SIX, STARS),
    SEVEN_STARS(SEVEN, STARS),
    EIGHT_STARS(EIGHT, STARS),
    NINE_STARS(NINE, STARS),
    TEN_STARS(TEN, STARS),
    JACK_STARS(JACK, STARS),
    QUEEN_STARS(QUEEN, STARS),
    KING_STARS(KING, STARS),
    ACE_STARS(ACE, STARS);

    private final CardRank rank;
    private final CardSuite suite;

    Card(CardRank rank, CardSuite suite) {
        this.rank = rank;
        this.suite = suite;
    }

    public CardRank getRank() {
        return rank;
    }

    public CardSuite getSuite() {
        return suite;
    }
}
