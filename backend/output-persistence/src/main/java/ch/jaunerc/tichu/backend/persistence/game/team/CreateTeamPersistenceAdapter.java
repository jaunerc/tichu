package ch.jaunerc.tichu.backend.persistence.game.team;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.domain.game.port.CreateTeamPort;
import ch.jaunerc.tichu.backend.persistence.game.player.PlayerEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTeamPersistenceAdapter implements CreateTeamPort {

    private final TeamRepository teamRepository;

    @Override
    public Team createTeam(Player player) {
        var team = new TeamEntity();
        team.setFirstPlayer(PlayerEntityMapper.map(player));
        return TeamEntityMapper.toDomain(teamRepository.save(team));
    }
}
