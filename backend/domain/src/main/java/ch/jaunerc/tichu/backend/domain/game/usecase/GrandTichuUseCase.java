package ch.jaunerc.tichu.backend.domain.game.usecase;

import ch.jaunerc.tichu.backend.domain.game.model.Game;

import java.util.UUID;

public interface GrandTichuUseCase {

    Game grandTichuByPlayer(UUID gameId, UUID playerId, boolean isGrandTichuCalled);
}
