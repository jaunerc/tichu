package ch.jaunerc.tichu.backend.domain.game.usecase;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PushCardUseCase {

    Optional<List<Card>> pushCard(UUID gameId, Card card, UUID fromPlayerId, int toPlayerNumber);
}
