package ch.jaunerc.tichu.backend.domain.game.port.input;

import java.util.UUID;

public interface ShuffleDeckInputPort {

    void shuffleDeck(UUID gameId);
}
