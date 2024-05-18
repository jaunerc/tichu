package ch.jaunerc.tichu.backend.persistence.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.port.output.SaveGameOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveGamePersistenceAdapter implements SaveGameOutputPort {

    private final GameRepository gameRepository;

    @Override
    public Game saveGame(Game game) {
        var savedGame = gameRepository.save(GameEntityMapper.map(game));
        return GameEntityMapper.toDomain(savedGame);
    }
}
