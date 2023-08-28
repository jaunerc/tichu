package ch.jaunerc.tichu.backend.domain.game.port;

import ch.jaunerc.tichu.backend.domain.game.model.Game;

import java.util.UUID;

public interface FindGameByIdPort {

    Game findGameById(UUID gameId);
}
