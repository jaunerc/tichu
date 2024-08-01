package ch.jaunerc.tichu.backend.domain.game.port.input;

import ch.jaunerc.tichu.backend.domain.game.model.Game;

import java.util.UUID;

public interface SmallTichuInputPort {

    Game smallTichuByPlayer(UUID gameId, UUID playerId, boolean isSmallTichuCalled);
}
