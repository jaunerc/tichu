package ch.jaunerc.tichu.backend.domain.game.port;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;

public interface CreateGamePort {

    Game createGame(GamePhase gamePhase);
}
