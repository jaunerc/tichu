package ch.jaunerc.tichu.backend.domain.player.port;

import ch.jaunerc.tichu.backend.domain.player.model.Player;

public interface CreatePlayerPort {

    Player createPlayer(String playerName);
}
