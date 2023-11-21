package ch.jaunerc.tichu.backend.domain.game.port;

import ch.jaunerc.tichu.backend.domain.game.model.Player;

public interface SavePlayerPort {

    void savePlayer(Player player);
}
