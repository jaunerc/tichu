package ch.jaunerc.tichu.backend.websocket;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.domain.game.usecase.PushCardUseCase;
import ch.jaunerc.tichu.backend.websocket.message.PushCardPlayerMessage;
import ch.jaunerc.tichu.backend.websocket.message.PushCardsServerMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class WebsocketController {

    private final PushCardUseCase pushCardUseCase;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/{gameId}/small-tichu/{playerId}")
    @SendTo("/topic/{gameId}/small-tichu") // replace with gamestate
    public void smallTichu(@DestinationVariable("gameId") String gameId,
                           @DestinationVariable("playerId") String playerId) {

    }

    @MessageMapping("/{gameId}/push-card/{playerId}")
    public void pushCard(@DestinationVariable("gameId") String gameId,
                         @DestinationVariable("playerId") String playerId,
                         @Payload PushCardPlayerMessage pushCardPlayerMessage) {
        var receivedCards = pushCardUseCase.pushCard(
                UUID.fromString(gameId),
                Card.valueOf(pushCardPlayerMessage.cardName()),
                UUID.fromString(playerId),
                pushCardPlayerMessage.recipientPlayerNumber()
        );

        receivedCards.ifPresent(
                cards -> simpMessagingTemplate.convertAndSend("/topic/" + gameId + "/push-card/" + playerId,
                new PushCardsServerMessage(
                        cards.stream()
                                .map(Enum::name)
                                .toList())));
    }
}
