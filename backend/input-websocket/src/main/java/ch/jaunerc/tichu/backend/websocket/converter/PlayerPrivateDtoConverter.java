package ch.jaunerc.tichu.backend.websocket.converter;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.websocket.api.model.PlayerPrivateStateDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlayerPrivateDtoConverter {

    public static PlayerPrivateStateDto convert(List<Card> cards) {
        var playerPrivateStateDto = new PlayerPrivateStateDto();
        playerPrivateStateDto.setCards(cards.stream()
                .map(Enum::name)
                .toList());
        return playerPrivateStateDto;
    }
}
