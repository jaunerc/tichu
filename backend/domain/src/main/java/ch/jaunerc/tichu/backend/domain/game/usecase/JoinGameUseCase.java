package ch.jaunerc.tichu.backend.domain.game.usecase;

import ch.jaunerc.tichu.backend.domain.game.model.JoinGame;

public interface JoinGameUseCase {

    JoinGame joinGame(String gameId, String userId);
}
