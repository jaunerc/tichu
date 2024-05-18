package ch.jaunerc.tichu.backend.domain.game.port.output;

import ch.jaunerc.tichu.backend.domain.game.model.Game;

import java.util.List;

public interface FindAllGamesOutputPort {

    List<Game> findAllGames();
}
