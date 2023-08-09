package ch.jaunerc.tichu.backend.persistence.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import ch.jaunerc.tichu.backend.domain.game.port.CreateGamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateGamePersistenceAdapter implements CreateGamePort {

    private final GameRepository gameRepository;

    @Override
    public Game createGame(GamePhase gamePhase) {
        var savedEntity = gameRepository.save(GameEntityMapper.map(gamePhase));
        return GameEntityMapper.toDomain(savedEntity);
    }
}
