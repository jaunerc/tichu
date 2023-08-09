package ch.jaunerc.tichu.backend.web.games;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class GameDtoMapperTest {

    @Test
    void map() {
        var uuid = "00000000-0000-0000-0000-000000000000";
        var game = new Game(UUID.fromString(uuid), null, null, null);

        var result = GameDtoMapper.map(game);

        assertThat(result.getId()).isEqualTo(uuid);
    }
}