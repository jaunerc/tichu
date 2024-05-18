package ch.jaunerc.tichu.backend.domain.game.port.output;

import ch.jaunerc.tichu.backend.domain.game.model.Game;

public interface SaveGameOutputPort {

    Game saveGame(Game game);
}
