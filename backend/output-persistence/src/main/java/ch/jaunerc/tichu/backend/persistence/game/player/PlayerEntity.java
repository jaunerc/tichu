package ch.jaunerc.tichu.backend.persistence.game.player;


import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity(name = "Player")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Convert(converter = CardsConverter.class)
    private List<Card> cards;
}
