package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.model.GamePhase.DEALING_CARDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TeamJoinerTest {

    @Test
    @DisplayName("should add the player at the first position of the first team when the first team is empty")
    void joinFirstOrSecondTeam_firstTeamIsEmpty_firstPlayerInFirstTeam() {
        var game = new Game.Builder(UUID.randomUUID(), DEALING_CARDS)
                .firstTeam(new Team(UUID.randomUUID(), null, null, 0))
                .secondTeam(new Team(UUID.randomUUID(), null, null, 0))
                .build();
        var player = mockPlayer();

        var result = TeamJoiner.joinFirstOrSecondTeam(game, player);

        assertThat(result.firstTeam().firstPlayer()).isEqualTo(player);
    }

    @Test
    @DisplayName("should add the player at the second position of the first team when the first player is already occupied")
    void joinFirstOrSecondTeam_firstTeamIsPartiallyEmpty_secondPlayerInFirstTeam() {
        var game = new Game.Builder(UUID.randomUUID(), DEALING_CARDS)
                .firstTeam(new Team(UUID.randomUUID(), mockPlayer(), null, 0))
                .secondTeam(new Team(UUID.randomUUID(), null, null, 0))
                .build();
        var player = mockPlayer();

        var result = TeamJoiner.joinFirstOrSecondTeam(game, player);

        assertThat(result.firstTeam().secondPlayer()).isEqualTo(player);
    }

    @Test
    @DisplayName("should add the player at the first position of the second team when the second team is empty")
    void joinFirstOrSecondTeam_onlySecondTeamHasCapacity_firstPositionInSecondTeam() {
        var game = new Game.Builder(UUID.randomUUID(), DEALING_CARDS)
                .firstTeam(new Team(UUID.randomUUID(), mockPlayer(), mockPlayer(), 0))
                .secondTeam(new Team(UUID.randomUUID(), null, null, 0))
                .build();
        var player = mockPlayer();

        var result = TeamJoiner.joinFirstOrSecondTeam(game, player);

        assertThat(result.secondTeam().firstPlayer()).isEqualTo(player);
    }

    @Test
    @DisplayName("should add the player at the second position of the second team when the first team is full and the first player is already occupied")
    void joinFirstOrSecondTeam_onlySecondTeamIsPartiallyOccupied_secondPositionInSecondTeam() {
        var game = new Game.Builder(UUID.randomUUID(), DEALING_CARDS)
                .firstTeam(new Team(UUID.randomUUID(), mockPlayer(), mockPlayer(), 0))
                .secondTeam(new Team(UUID.randomUUID(), mockPlayer(), null, 0))
                .build();
        var player = mockPlayer();

        var result = TeamJoiner.joinFirstOrSecondTeam(game, player);

        assertThat(result.secondTeam().secondPlayer()).isEqualTo(player);
    }

    @Test
    @DisplayName("should throw an exception when there is no team capacity")
    void joinFirstOrSecondTeam_noCapacity_exception() {
        var game = new Game.Builder(UUID.randomUUID(), DEALING_CARDS)
                .firstTeam(new Team(UUID.randomUUID(), mockPlayer(), mockPlayer(), 0))
                .secondTeam(new Team(UUID.randomUUID(), mockPlayer(), mockPlayer(), 0))
                .build();
        var player = mockPlayer();

        assertThrows(IllegalStateException.class, () -> TeamJoiner.joinFirstOrSecondTeam(game, player));
    }

    private static Player mockPlayer() {
        return new Player.Builder(UUID.randomUUID()).build();
    }
}