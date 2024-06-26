package ch.jaunerc.tichu.backend.persistence.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindAllGamesOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllGamesPersistenceAdapter implements FindAllGamesOutputPort {

    private final GameRepository gameRepository;

    @Override
    public List<Game> findAllGames() {
        return gameRepository.findAll().stream()
                .map(GameEntityMapper::toDomain)
                .toList();
    }
}
