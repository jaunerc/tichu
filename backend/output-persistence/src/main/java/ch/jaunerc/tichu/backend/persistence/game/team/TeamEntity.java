package ch.jaunerc.tichu.backend.persistence.game.team;

import ch.jaunerc.tichu.backend.persistence.game.player.PlayerEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "Team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "firstPlayerId", referencedColumnName = "id")
    private PlayerEntity firstPlayer;

    @OneToOne
    @JoinColumn(name = "secondPlayerId", referencedColumnName = "id")
    private PlayerEntity secondPlayer;
}
