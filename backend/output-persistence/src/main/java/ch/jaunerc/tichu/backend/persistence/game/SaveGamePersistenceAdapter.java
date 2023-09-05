package ch.jaunerc.tichu.backend.persistence.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.port.SaveGamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveGamePersistenceAdapter implements SaveGamePort {

    private final GameRepository gameRepository;

    @Override
    public Game saveGame(Game game) {
        var savedGame = gameRepository.save(GameEntityMapper.map(game));
        return GameEntityMapper.toDomain(savedGame);
    }
}
