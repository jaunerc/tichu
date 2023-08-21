package ch.jaunerc.tichu.backend.persistence.game.team;

import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.persistence.game.player.PlayerEntityMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamEntityMapper {

    public static TeamEntity map(Team team) {
        var teamEntity = new TeamEntity();
        teamEntity.setFirstPlayer(PlayerEntityMapper.map(team.firstPlayer()));
        teamEntity.setSecondPlayer(PlayerEntityMapper.map(team.secondPlayer()));
        return teamEntity;
    }

    public static Team toDomain(TeamEntity teamEntity) {
        return new Team(
                PlayerEntityMapper.toDomain(teamEntity.getFirstPlayer()),
                PlayerEntityMapper.toDomain(teamEntity.getSecondPlayer()),
                0 // TODO store points in the entity
        );
    }
}
