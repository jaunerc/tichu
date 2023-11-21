package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.port.ChangeGamePhaseUseCase;
import ch.jaunerc.tichu.backend.domain.game.port.FindGameByIdPort;
import ch.jaunerc.tichu.backend.domain.game.port.SaveGamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.model.GamePhase.*;

@Service
@RequiredArgsConstructor
public class ChangeGamePhaseService implements ChangeGamePhaseUseCase {

    private final FindGameByIdPort findGameByIdPort;
    private final SaveGamePort saveGamePort;

    @Override
    public void changeGamePhase(UUID gameId) {
        var gameWithNextPhase = toNextGamePhase(findGameByIdPort.findGameById(gameId));
        saveGamePort.saveGame(gameWithNextPhase);
    }

    private Game toNextGamePhase(Game game) {
        var gameBuilder = Game.Builder.of(game);
        switch (game.gamePhase()) {
            case PLAYERS_JOINING -> gameBuilder
                    .gamePhase(DEALING_CARDS);
            case DEALING_CARDS -> gameBuilder
                    .gamePhase(FIRST_EIGHT_CARDS_ARE_DEALT);
            case FIRST_EIGHT_CARDS_ARE_DEALT -> gameBuilder
                    .gamePhase(ALL_CARDS_ARE_DEALT);
            case ALL_CARDS_ARE_DEALT -> gameBuilder
                    .gamePhase(GAME_IS_RUNNING);
            case GAME_IS_RUNNING -> gameBuilder
                    .gamePhase(GAME_ENDED);
            default ->
                    throw new IllegalStateException("the game has ended and it is not possible to change the game phase anymore");
        }
        return gameBuilder.build();
    }
}
