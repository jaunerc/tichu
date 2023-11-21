package ch.jaunerc.tichu.backend.websocket;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;

import java.util.List;

public record DealCardsMessage(List<Card> cards) {
}
