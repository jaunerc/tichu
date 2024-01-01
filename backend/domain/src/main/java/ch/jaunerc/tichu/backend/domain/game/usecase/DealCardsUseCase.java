package ch.jaunerc.tichu.backend.domain.game.usecase;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;

import java.util.List;
import java.util.UUID;

public interface DealCardsUseCase {

    List<Card> dealCards(UUID gameId, UUID playerId);
}
