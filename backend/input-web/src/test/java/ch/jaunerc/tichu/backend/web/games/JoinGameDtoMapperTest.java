package ch.jaunerc.tichu.backend.web.games;

import ch.jaunerc.tichu.backend.domain.game.model.JoinGame;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class JoinGameDtoMapperTest {

    @Test
    void map() {
        var uuid = "00000000-0000-0000-0000-000000000000";
        var joinGame = new JoinGame(UUID.fromString(uuid));

        var result = JoinGameDtoMapper.map(joinGame);

        assertThat(result.getPlayerId()).isEqualTo(uuid);
    }
}