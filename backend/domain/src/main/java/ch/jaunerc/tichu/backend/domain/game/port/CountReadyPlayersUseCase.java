package ch.jaunerc.tichu.backend.domain.game.port;

import java.util.UUID;

public interface CountReadyPlayersUseCase {

    int countReadyPlayers(UUID gameId);
}
