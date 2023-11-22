package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import ch.jaunerc.tichu.backend.domain.game.port.CreateGamePort;
import ch.jaunerc.tichu.backend.domain.game.usecase.CreateGameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateGameService implements CreateGameUseCase {

    private final CreateGamePort createGamePort;

    @Override
    public Game createGame() {
        return createGamePort.createGame(GamePhase.PLAYERS_JOINING);
    }
}
