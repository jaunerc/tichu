package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.port.input.FindAllGamesInputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindAllGamesOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllGamesUseCase implements FindAllGamesInputPort {

    private final FindAllGamesOutputPort findAllGamesOutputPort;

    @Override
    public List<Game> findAllGames() {
        return findAllGamesOutputPort.findAllGames();
    }
}
