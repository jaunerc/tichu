package ch.jaunerc.tichu.backend.domain.game.usecase;

import ch.jaunerc.tichu.backend.domain.game.model.GrandTichuCall;

import java.util.UUID;

public interface GrandTichuUseCase {

    GrandTichuCall grandTichuByPlayer(UUID gameId, UUID playerId, boolean isGrandTichuCalled);
}
