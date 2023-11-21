package ch.jaunerc.tichu.backend.domain.game.port;

import java.util.UUID;

public interface ReadyPlayerUseCase {

    int updateReadyPlayers(UUID gameId);
}
