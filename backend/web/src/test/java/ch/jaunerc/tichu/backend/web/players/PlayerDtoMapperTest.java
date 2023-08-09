package ch.jaunerc.tichu.backend.web.players;

import ch.jaunerc.tichu.backend.domain.player.model.Player;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerDtoMapperTest {

    @Test
    void map() {
        var uuid = "00000000-0000-0000-0000-000000000000";
        var playerName = "Champion";
        var player = new Player(UUID.fromString(uuid), playerName);

        var result = PlayerDtoMapper.map(player);

        assertThat(result.getId()).isEqualTo(uuid);
        assertThat(result.getName()).isEqualTo(playerName);
    }
}