package ch.jaunerc.tichu.backend.domain.game.port.input;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;

import java.util.List;
import java.util.UUID;

public interface DealCardsInputPort {

    List<Card> dealCards(UUID gameId, UUID playerId);
}
