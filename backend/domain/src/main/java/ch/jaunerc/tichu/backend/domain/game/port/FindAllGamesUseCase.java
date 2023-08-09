package ch.jaunerc.tichu.backend.domain.game.port;

import ch.jaunerc.tichu.backend.domain.game.model.Game;

import java.util.List;

public interface FindAllGamesUseCase {

    List<Game> findAllGames();
}
