package ch.jaunerc.tichu.backend.persistence.game.player;

import ch.jaunerc.tichu.backend.domain.game.model.GamePlayer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.model.card.Card.*;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerEntityMapperTest {

    @Test
    @DisplayName("should create a player entity without setting the id")
    void map() {
        var domainPlayer = new GamePlayer(UUID.randomUUID(), List.of(EIGHT_JADE, NINE_PAGODAS));
        var player = PlayerEntityMapper.map(domainPlayer);

        assertThat(player.getCards()).containsExactly(EIGHT_JADE, NINE_PAGODAS);
        assertThat(player.getId()).isNull();
    }

    @Test
    void toDomain() {
        var uuid = UUID.randomUUID();
        var cards = List.of(JACK_JADE, JACK_SWORDS);
        var playerEntity = new PlayerEntity(uuid, cards);

        var result = PlayerEntityMapper.toDomain(playerEntity);

        assertThat(result.uuid()).isEqualTo(uuid);
        assertThat(result.cards()).containsExactly(JACK_JADE, JACK_SWORDS);
    }
}