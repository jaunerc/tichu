package ch.jaunerc.tichu.backend.persistence.game.player;


import ch.jaunerc.tichu.backend.domain.game.model.PlayerSeatId;
import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.persistence.user.UserEntity;
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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private UserEntity user;

    private PlayerSeatId playerSeatId;

    private boolean grandTichuCalled;

    private boolean smallTichuCalled;

    private boolean firstEightCardsReceived;

    @Convert(converter = CardsConverter.class)
    private List<Card> cards;

    @Convert(converter = CardsConverter.class)
    private List<Card> pushedCards;

    @Convert(converter = CardsConverter.class)
    private List<Card> receivedCards;
}
