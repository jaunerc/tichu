package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.*;
import ch.jaunerc.tichu.backend.domain.game.port.input.JoinGameInputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.CreatePlayerOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindGameByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindUserByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.SaveGameOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.PlayerCollector.listOfPlayersBy;
import static ch.jaunerc.tichu.backend.domain.game.TeamJoiner.joinFirstOrSecondTeam;

@Service
@RequiredArgsConstructor
public class JoinGameUseCase implements JoinGameInputPort {

    private final FindGameByIdOutputPort findGameByIdPort;
    private final FindUserByIdOutputPort findUserByIdOutputPort;
    private final CreatePlayerOutputPort createPlayerOutputPort;
    private final SaveGameOutputPort saveGameOutputPort;

    @Override
    @Transactional
    public JoinGame joinGame(String gameId, String userId) {
        var game = findGameByIdPort.findGameById(UUID.fromString(gameId));
        var user = findUserByIdOutputPort.findUserById(UUID.fromString(userId));
        var player = createPlayerOutputPort.createPlayer(user, nextPlayerSeatId(game));

        var gameJoined = tryToJoinTheGame(game, player);
        var persistedGame = saveGameOutputPort.saveGame(gameJoined);

        return new JoinGame(persistedGame.gameId(), player.uuid(), player.playerSeatId());
    }

    private PlayerSeatId nextPlayerSeatId(Game game) {
        var playerCount = listOfPlayersBy(game).size();
        return PlayerSeatId
                .getByIndex(playerCount)
                .orElseThrow(() -> new IllegalArgumentException("Cannot get the next player seat id for the playerCount=" + playerCount));
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
