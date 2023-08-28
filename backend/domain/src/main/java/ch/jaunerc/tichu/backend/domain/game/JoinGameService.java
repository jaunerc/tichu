package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.JoinGame;
import ch.jaunerc.tichu.backend.domain.game.port.FindGameByIdPort;
import ch.jaunerc.tichu.backend.domain.game.port.JoinGameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JoinGameService implements JoinGameUseCase {

    private final FindGameByIdPort findGameByIdPort;

    @Override
    public JoinGame joinGame(String gameId, String userId) {
        var game = findGameByIdPort.findGameById(UUID.fromString(gameId)); // TODO could be invalid uuid input
        return null;
    }
}
