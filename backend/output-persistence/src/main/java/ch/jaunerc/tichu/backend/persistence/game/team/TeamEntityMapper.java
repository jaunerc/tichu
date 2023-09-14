package ch.jaunerc.tichu.backend.persistence.game.team;

import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.persistence.game.player.PlayerEntityMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamEntityMapper {

    public static TeamEntity map(Team team) {
        var teamEntity = new TeamEntity();

        if (team.firstPlayer() != null) {
            teamEntity.setFirstPlayer(PlayerEntityMapper.map(team.firstPlayer()));
        }

        if (team.secondPlayer() != null) {
            teamEntity.setSecondPlayer(PlayerEntityMapper.map(team.secondPlayer()));
        }

        return teamEntity;
    }

    public static Team toDomain(TeamEntity teamEntity) {
        var teamBuilder = new Team.Builder(teamEntity.getId());

        if (teamEntity.getFirstPlayer() != null) {
            teamBuilder.firstPlayer(PlayerEntityMapper.toDomain(teamEntity.getFirstPlayer()));
        }

        if (teamEntity.getSecondPlayer() != null) {
            teamBuilder.secondPlayer(PlayerEntityMapper.toDomain(teamEntity.getSecondPlayer()));
        }

        teamBuilder.points(0); // TODO store points in the entity
        return teamBuilder.build();
    }
}
