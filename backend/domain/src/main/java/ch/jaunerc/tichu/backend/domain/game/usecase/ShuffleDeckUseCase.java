package ch.jaunerc.tichu.backend.domain.game.usecase;

import java.util.UUID;

public interface ShuffleDeckUseCase {

    void shuffleDeck(UUID gameId);
}
