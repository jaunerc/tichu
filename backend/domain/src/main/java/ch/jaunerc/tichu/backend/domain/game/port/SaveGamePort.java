package ch.jaunerc.tichu.backend.domain.game.port;

import ch.jaunerc.tichu.backend.domain.game.model.Game;

public interface SaveGamePort {

    Game saveGame(Game game);
}
