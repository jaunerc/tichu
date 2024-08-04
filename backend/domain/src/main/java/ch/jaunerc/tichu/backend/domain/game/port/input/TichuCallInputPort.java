package ch.jaunerc.tichu.backend.domain.game.port.input;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.TichuCallResult;

import java.util.UUID;

public interface TichuCallInputPort {
    Game tichuCallByPlayer(UUID gameId, UUID playerId, TichuCallResult tichuCallResult);
}
