package ch.jaunerc.tichu.backend.domain.game.port;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.Team;

public interface CreateGameUseCase {

    Game createGame();
}
