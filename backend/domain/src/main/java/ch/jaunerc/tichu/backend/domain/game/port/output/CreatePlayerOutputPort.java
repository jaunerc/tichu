package ch.jaunerc.tichu.backend.domain.game.port.output;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.PlayerSeatId;
import ch.jaunerc.tichu.backend.domain.user.model.User;

public interface CreatePlayerOutputPort {

    Player createPlayer(User user, PlayerSeatId playerSeatId);
}
