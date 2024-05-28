package ch.jaunerc.tichu.backend.websocket.controller;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.domain.game.port.input.DealCardsInputPort;
import ch.jaunerc.tichu.backend.websocket.message.PlayerPrivateStateServerMessage;
import ch.jaunerc.tichu.backend.websocket.message.game.PlayerPrivateDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class DealCardsWebsocketController {

    private final DealCardsInputPort dealCardsInputPort;

    @MessageMapping("/{gameId}/deal-cards/{playerId}")
    @SendTo("/topic/{gameId}/state/{playerId}")
    public PlayerPrivateStateServerMessage dealCardsToUser(@DestinationVariable("gameId") String gameId,
                                                           @DestinationVariable("playerId") String playerId) {
        var playerPrivateDto = PlayerPrivateDtoConverter.convert(dealCards(gameId, playerId));

        return new PlayerPrivateStateServerMessage(playerPrivateDto);
    }

    private List<Card> dealCards(String gameId, String playerId) {
        return dealCardsInputPort.dealCards(UUID.fromString(gameId), UUID.fromString(playerId));
    }
}
