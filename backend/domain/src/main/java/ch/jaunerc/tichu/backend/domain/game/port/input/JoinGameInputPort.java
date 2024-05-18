package ch.jaunerc.tichu.backend.domain.game.port.input;

import ch.jaunerc.tichu.backend.domain.game.model.JoinGame;

public interface JoinGameInputPort {

    JoinGame joinGame(String gameId, String userId);
}
