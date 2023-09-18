package ch.jaunerc.tichu.backend.web.games;

import ch.jaunerc.tichu.backend.domain.game.model.JoinGame;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class JoinGameDtoMapperTest {

    @Test
    void map() {
        var gameId = "00000000-0000-0000-0000-000000000000";
        var playerId = "00000000-0000-0000-0000-000000000001";
        var joinGame = new JoinGame(
                UUID.fromString(gameId),
                UUID.fromString(playerId));

        var result = JoinGameDtoMapper.map(joinGame);

        assertThat(result.getGameId()).isEqualTo(gameId);
        assertThat(result.getPlayerId()).isEqualTo(playerId);
    }
}