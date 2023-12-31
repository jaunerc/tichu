package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.JoinGame;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.domain.game.port.CreatePlayerPort;
import ch.jaunerc.tichu.backend.domain.game.port.FindGameByIdPort;
import ch.jaunerc.tichu.backend.domain.game.port.FindUserByIdPort;
import ch.jaunerc.tichu.backend.domain.game.port.SaveGamePort;
import ch.jaunerc.tichu.backend.domain.game.usecase.JoinGameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.TeamJoiner.joinFirstOrSecondTeam;

@Service
@RequiredArgsConstructor
public class JoinGameService implements JoinGameUseCase {

    private final FindGameByIdPort findGameByIdPort;
    private final FindUserByIdPort findUserByIdPort;
    private final CreatePlayerPort createPlayerPort;
    private final SaveGamePort saveGamePort;

    @Override
    @Transactional
    public JoinGame joinGame(String gameId, String userId) {
        var game = findGameByIdPort.findGameById(UUID.fromString(gameId));
        var user = findUserByIdPort.findUserById(UUID.fromString(userId));
        var player = createPlayerPort.createPlayer(user);

        var gameJoined = tryToJoinTheGame(game, player);
        var persistedGame = saveGamePort.saveGame(gameJoined);

        return new JoinGame(persistedGame.gameId(), player.uuid());
    }

    private Game tryToJoinTheGame(Game game, Player player) {
        if (game.firstTeam() == null) {
            return Game.Builder.of(game)
                    .firstTeam(new Team(null, player, null, 0))
                    .build();
        } else if (game.secondTeam() == null) {
            return Game.Builder.of(game)
                    .secondTeam(new Team(null, player, null, 0))
                    .build();
        }

        return joinFirstOrSecondTeam(game, player);
    }

}
