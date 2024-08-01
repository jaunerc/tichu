package ch.jaunerc.tichu.backend.domain.game.port.input;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.TichuCallResult;

import java.util.UUID;

public interface TichuCallInputPort {

    Game smallTichuByPlayer(UUID gameId, UUID playerId, boolean isSmallTichuCalled);

    Game grandTichuByPlayer(UUID gameId, UUID playerId, boolean isGrandTichuCalled);

    Game tichuCallByPlayer(UUID gameId, UUID playerId, TichuCallResult tichuCallResult);
}
