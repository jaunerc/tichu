package ch.jaunerc.tichu.backend.domain.game.port.input;

import java.util.UUID;

public interface ChangeGamePhaseInputPort {

    void changeGamePhase(UUID gameId);
}
