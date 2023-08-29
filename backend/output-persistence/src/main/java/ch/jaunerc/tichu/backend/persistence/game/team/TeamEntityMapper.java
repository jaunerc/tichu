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
                teamEntity.getId(),
                teamEntity.getFirstPlayer() != null ? PlayerEntityMapper.toDomain(teamEntity.getFirstPlayer()) : null,
                teamEntity.getSecondPlayer() != null ? PlayerEntityMapper.toDomain(teamEntity.getSecondPlayer()) : null,
                0 // TODO store points in the entity
        );
    }
}
