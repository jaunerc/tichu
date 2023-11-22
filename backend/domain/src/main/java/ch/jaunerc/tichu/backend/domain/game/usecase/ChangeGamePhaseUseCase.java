package ch.jaunerc.tichu.backend.domain.game.usecase;

import java.util.UUID;

public interface ChangeGamePhaseUseCase {

    void changeGamePhase(UUID gameId);
}
