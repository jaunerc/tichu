package ch.jaunerc.tichu.backend.domain.game.port.output;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;

public interface CreateGameOutputPort {

    Game createGame(GamePhase gamePhase);
}
