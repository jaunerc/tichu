package ch.jaunerc.tichu.backend.websocket.message;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;

import java.util.List;

public record DealCardsServerMessage(List<Card> cards) {
}
