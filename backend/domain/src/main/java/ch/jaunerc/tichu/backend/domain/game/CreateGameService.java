package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import ch.jaunerc.tichu.backend.domain.game.port.output.CreateGameOutputPort;
import ch.jaunerc.tichu.backend.domain.game.usecase.CreateGameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateGameService implements CreateGameUseCase {

    private final CreateGameOutputPort createGameOutputPort;

    @Override
    public Game createGame() {
        return createGameOutputPort.createGame(GamePhase.PLAYERS_JOINING);
    }
}
