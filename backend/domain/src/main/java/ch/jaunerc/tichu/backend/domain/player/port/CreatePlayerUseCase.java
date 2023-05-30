package ch.jaunerc.tichu.backend.domain.player.port;

import ch.jaunerc.tichu.backend.domain.player.model.Player;

public interface CreatePlayerUseCase {

    Player createPlayer(String playerName);
}
