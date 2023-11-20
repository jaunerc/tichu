package ch.jaunerc.tichu.backend.persistence.game.team;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.domain.user.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class TeamEntityMapperTest {

    @Test
    void map() {
        var uuidPlayerA = UUID.randomUUID();
        var uuidPlayerB = UUID.randomUUID();
        var uuidTeam = UUID.randomUUID();
        var team = new Team.Builder(uuidTeam)
                .firstPlayer(new Player(uuidPlayerA, new User(null, null), List.of()))
                .secondPlayer(new Player(uuidPlayerB, new User(null, null), List.of()))
                .build();

        var result = TeamEntityMapper.map(team);

        assertThat(result.getId()).isEqualTo(uuidTeam);
        assertThat(result.getFirstPlayer().getId()).isEqualTo(uuidPlayerA);
        assertThat(result.getSecondPlayer().getId()).isEqualTo(uuidPlayerB);
    }
}