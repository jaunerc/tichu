package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.port.FindAllGamesPort;
import ch.jaunerc.tichu.backend.domain.game.usecase.FindAllGamesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllGamesService implements FindAllGamesUseCase {

    private final FindAllGamesPort findAllGamesPort;

    @Override
    public List<Game> findAllGames() {
        return findAllGamesPort.findAllGames();
    }
}
