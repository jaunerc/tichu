package ch.jaunerc.tichu.backend.domain.game.port.input;

import ch.jaunerc.tichu.backend.domain.game.model.Game;

import java.util.List;

public interface FindAllGamesInputPort {

    List<Game> findAllGames();
}
