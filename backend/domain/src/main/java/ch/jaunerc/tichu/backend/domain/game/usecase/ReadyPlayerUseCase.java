package ch.jaunerc.tichu.backend.domain.game.usecase;

import java.util.UUID;

public interface ReadyPlayerUseCase {

    int updateReadyPlayers(UUID gameId);
}
