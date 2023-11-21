package ch.jaunerc.tichu.backend.domain.game.port;

import java.util.UUID;

public interface ChangeGamePhaseUseCase {

    void changeGamePhase(UUID gameId);
}
