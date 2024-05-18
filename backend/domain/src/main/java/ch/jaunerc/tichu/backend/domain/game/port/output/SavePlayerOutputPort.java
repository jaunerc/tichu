package ch.jaunerc.tichu.backend.domain.game.port.output;

import ch.jaunerc.tichu.backend.domain.game.model.Player;

public interface SavePlayerOutputPort {

    void savePlayer(Player player);
}
