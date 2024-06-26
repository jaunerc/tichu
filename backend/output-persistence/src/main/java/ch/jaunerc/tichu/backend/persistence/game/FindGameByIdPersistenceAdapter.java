package ch.jaunerc.tichu.backend.persistence.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindGameByIdOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindGameByIdPersistenceAdapter implements FindGameByIdOutputPort {

    private final GameRepository gameRepository;

    @Override
    public Game findGameById(UUID gameId) {
        return gameRepository.findById(gameId)
                .map(GameEntityMapper::toDomain)
                .orElseThrow();
    }
}
