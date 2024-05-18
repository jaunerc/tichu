package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import ch.jaunerc.tichu.backend.domain.game.port.input.CreateGameInputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.CreateGameOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateGameUseCase implements CreateGameInputPort {

    private final CreateGameOutputPort createGameOutputPort;

    @Override
    public Game createGame() {
        return createGameOutputPort.createGame(GamePhase.PLAYERS_JOINING);
    }
}
