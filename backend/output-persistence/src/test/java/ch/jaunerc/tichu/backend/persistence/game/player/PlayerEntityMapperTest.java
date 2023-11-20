package ch.jaunerc.tichu.backend.persistence.game.player;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.user.model.User;
import ch.jaunerc.tichu.backend.persistence.user.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.model.card.Card.*;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerEntityMapperTest {

    @Test
    @DisplayName("should create a player entity and setting the id if present")
    void map() {
        var user = new User(UUID.randomUUID(), null);
        var domainPlayer = new Player(UUID.randomUUID(), user, List.of(EIGHT_JADE, NINE_PAGODAS));
        var player = PlayerEntityMapper.map(domainPlayer);

        assertThat(player.getCards()).containsExactly(EIGHT_JADE, NINE_PAGODAS);
        assertThat(player.getId()).isNotNull();
    }

    @Test
    @DisplayName("should create a player entity without setting the id")
    void map_nullUserId() {
        var user = new User(UUID.randomUUID(), null);
        var domainPlayer = new Player(null, user, List.of(EIGHT_JADE, NINE_PAGODAS));
        var player = PlayerEntityMapper.map(domainPlayer);

        assertThat(player.getCards()).containsExactly(EIGHT_JADE, NINE_PAGODAS);
        assertThat(player.getId()).isNull();
    }

    @Test
    void toDomain() {
        var uuid = UUID.randomUUID();
        var cards = List.of(JACK_JADE, JACK_SWORDS);
        var user = new UserEntity();
        user.setId(UUID.randomUUID());
        var playerEntity = new PlayerEntity(uuid, user, cards);

        var result = PlayerEntityMapper.toDomain(playerEntity);

        assertThat(result.uuid()).isEqualTo(uuid);
        assertThat(result.cards()).containsExactly(JACK_JADE, JACK_SWORDS);
    }
}