package ch.jaunerc.tichu.backend.domain.game.port.output;

import ch.jaunerc.tichu.backend.domain.game.model.Player;

import java.util.UUID;

public interface SendPlayerPrivateStateOutputPort {

    void sendPlayerPrivateState(UUID gameId, Player player);
}
