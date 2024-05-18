package ch.jaunerc.tichu.backend.domain.game.port.output;

import ch.jaunerc.tichu.backend.domain.game.model.Game;

import java.util.UUID;

public interface FindGameByIdOutputPort {

    Game findGameById(UUID gameId);
}
