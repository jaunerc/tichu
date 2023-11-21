package ch.jaunerc.tichu.backend.domain.game.port;

import java.util.UUID;

public interface ShuffleDeckUseCase {

    void shuffleDeck(UUID gameId);
}
