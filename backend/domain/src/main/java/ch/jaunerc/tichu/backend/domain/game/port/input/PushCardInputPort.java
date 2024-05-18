package ch.jaunerc.tichu.backend.domain.game.port.input;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PushCardInputPort {

    Optional<List<Card>> pushCard(UUID gameId, Card card, UUID fromPlayerId, int toPlayerNumber);
}
