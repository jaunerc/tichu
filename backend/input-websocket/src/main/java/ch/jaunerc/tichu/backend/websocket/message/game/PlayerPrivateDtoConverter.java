package ch.jaunerc.tichu.backend.websocket.message.game;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlayerPrivateDtoConverter {

    public static PlayerPrivateDto convert(List<Card> cards) {
        return new PlayerPrivateDto(
                cards.stream()
                        .map(Enum::name)
                        .toList(),
                List.of() // TODO implement received cards
        );
    }
}
