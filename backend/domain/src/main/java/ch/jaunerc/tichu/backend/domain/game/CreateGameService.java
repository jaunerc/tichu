package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.domain.game.port.CreateGameUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateGameService implements CreateGameUseCase {
    @Override
    public Game createGame() {
        return new Game(UUID.randomUUID(), null, null, GamePhase.DEALING_CARDS);
    }
}
