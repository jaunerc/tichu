package ch.jaunerc.tichu.backend.persistence.game;

import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameEntityMapperTest {

    @Test
    @DisplayName("should create a game entity without setting the id")
    void map() {
        var result = GameEntityMapper.map(GamePhase.DEALING_CARDS);

        assertThat(result.getGamePhase()).isEqualTo(GamePhase.DEALING_CARDS);
        assertThat(result.getId()).isNull();
    }
}
