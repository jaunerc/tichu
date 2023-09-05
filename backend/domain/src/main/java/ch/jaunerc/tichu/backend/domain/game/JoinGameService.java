package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.JoinGame;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.port.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.TeamJoiner.joinFirstOrSecondTeam;

@Service
@RequiredArgsConstructor
public class JoinGameService implements JoinGameUseCase {

    private final FindGameByIdPort findGameByIdPort;
    private final CreatePlayerPort createPlayerPort;
    private final CreateTeamPort createTeamPort;
    private final SaveGamePort saveGamePort;

    @Override
    public JoinGame joinGame(String gameId, String userId) {
        var game = findGameByIdPort.findGameById(UUID.fromString(gameId));
        var player = createPlayerPort.createPlayer();

        var gameJoined = tryToJoinTheGame(game, player);

        saveGamePort.saveGame(gameJoined);

        return new JoinGame(player.uuid());
    }

    private Game tryToJoinTheGame(Game game, Player player) {
        if (game.firstTeam() == null) {
            return Game.Builder.of(game)
                    .firstTeam(createTeamPort.createTeam(player))
                    .build();
        } else if (game.secondTeam() == null) {
            return Game.Builder.of(game)
                    .secondTeam(createTeamPort.createTeam(player))
                    .build();
        }

        return joinFirstOrSecondTeam(game, player);
    }

}
