package ch.jaunerc.tichu.backend.domain.game.port.input;

import ch.jaunerc.tichu.backend.domain.game.model.Game;

import java.util.UUID;

public interface GrandTichuInputPort {

    Game grandTichuByPlayer(UUID gameId, UUID playerId, boolean isGrandTichuCalled);
}
