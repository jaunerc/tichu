package ch.jaunerc.tichu.backend.persistence.game;

import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import ch.jaunerc.tichu.backend.persistence.game.team.TeamEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "Game")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private GamePhase gamePhase;

    @OneToOne
    @JoinColumn(name = "firstTeamId", referencedColumnName = "id")
    private TeamEntity firstTeam;

    @OneToOne
    @JoinColumn(name = "secondTeamId", referencedColumnName = "id")
    private TeamEntity secondTeam;
}
