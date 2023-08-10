package ch.jaunerc.tichu.backend.persistence.game;

import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class GameEntityMapperTest {

    @Test
    @DisplayName("should create a game entity without setting the id")
    void map() {
        var result = GameEntityMapper.map(GamePhase.DEALING_CARDS);

        assertThat(result.getGamePhase()).isEqualTo(GamePhase.DEALING_CARDS);
        assertThat(result.getId()).isNull();
    }

    @Test
    void toDomain() {
        var uuid = UUID.randomUUID();
        var gameEntity = new GameEntity();
        gameEntity.setId(uuid);
        gameEntity.setGamePhase(GamePhase.GAME_IS_RUNNING);

        var result = GameEntityMapper.toDomain(gameEntity);

        assertThat(result.gameId()).isEqualTo(uuid);
        assertThat(result.gamePhase()).isEqualTo(GamePhase.GAME_IS_RUNNING);
    }
}
