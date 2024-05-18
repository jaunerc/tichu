package ch.jaunerc.tichu.backend.domain.game.port.input;

import java.util.UUID;

public interface ReadyPlayerInputPort {

    int updateReadyPlayers(UUID gameId);
}
