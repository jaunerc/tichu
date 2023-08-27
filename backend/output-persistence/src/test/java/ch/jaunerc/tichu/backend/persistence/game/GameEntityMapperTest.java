package ch.jaunerc.tichu.backend.persistence.game;

import ch.jaunerc.tichu.backend.persistence.game.player.PlayerEntity;
import ch.jaunerc.tichu.backend.persistence.game.team.TeamEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.model.GamePhase.DEALING_CARDS;
import static ch.jaunerc.tichu.backend.domain.game.model.GamePhase.GAME_IS_RUNNING;
import static org.assertj.core.api.Assertions.assertThat;

class GameEntityMapperTest {

    @Test
    @DisplayName("should create a game entity without setting the id")
    void map() {
        var result = GameEntityMapper.map(DEALING_CARDS);

        assertThat(result.getGamePhase()).isEqualTo(DEALING_CARDS);
        assertThat(result.getId()).isNull();
    }

    @Test
    void toDomain() {
        var uuid = UUID.randomUUID();
        var gameEntity = createGameEntity(uuid);

        var result = GameEntityMapper.toDomain(gameEntity);

        assertThat(result.gameId()).isEqualTo(uuid);
        assertThat(result.gamePhase()).isEqualTo(GAME_IS_RUNNING);
        assertThat(result.firstTeam()).isNotNull();
        assertThat(result.secondTeam()).isNotNull();
    }

    @Test
    void toDomain_nullTeams() {
        var uuid = UUID.randomUUID();
        var gameEntity = createGameEntity(uuid);
        gameEntity.setFirstTeam(null);
        gameEntity.setSecondTeam(null);

        var result = GameEntityMapper.toDomain(gameEntity);

        assertThat(result.gameId()).isEqualTo(uuid);
        assertThat(result.gamePhase()).isEqualTo(GAME_IS_RUNNING);
        assertThat(result.firstTeam()).isNull();
        assertThat(result.secondTeam()).isNull();
    }

    private static GameEntity createGameEntity(UUID uuid) {
        var gameEntity = new GameEntity();
        gameEntity.setId(uuid);
        gameEntity.setGamePhase(GAME_IS_RUNNING);
        var firstTeam = new TeamEntity();
        firstTeam.setFirstPlayer(new PlayerEntity());
        firstTeam.setSecondPlayer(new PlayerEntity());
        gameEntity.setFirstTeam(firstTeam);
        var secondTeam = new TeamEntity();
        secondTeam.setFirstPlayer(new PlayerEntity());
        secondTeam.setSecondPlayer(new PlayerEntity());
        gameEntity.setSecondTeam(secondTeam);
        return gameEntity;
    }
}
