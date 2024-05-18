package ch.jaunerc.tichu.backend.domain.game.port.output;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;

public interface CreateTeamOutputPort {

    Team createTeam(Player player);
}
