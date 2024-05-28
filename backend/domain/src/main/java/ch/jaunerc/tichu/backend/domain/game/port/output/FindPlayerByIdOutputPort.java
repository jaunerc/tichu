package ch.jaunerc.tichu.backend.domain.game.port.output;

import ch.jaunerc.tichu.backend.domain.game.model.Player;

import java.util.UUID;

public interface FindPlayerByIdOutputPort {

    Player findPlayerById(UUID playerId);
}
