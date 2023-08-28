package ch.jaunerc.tichu.backend.domain.game.port;

import ch.jaunerc.tichu.backend.domain.game.model.JoinGame;

public interface JoinGameUseCase {

    JoinGame joinGame(String gameId, String userId);
}
