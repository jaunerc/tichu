package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindAllGamesOutputPort;
import ch.jaunerc.tichu.backend.domain.game.usecase.FindAllGamesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllGamesService implements FindAllGamesUseCase {

    private final FindAllGamesOutputPort findAllGamesOutputPort;

    @Override
    public List<Game> findAllGames() {
        return findAllGamesOutputPort.findAllGames();
    }
}
