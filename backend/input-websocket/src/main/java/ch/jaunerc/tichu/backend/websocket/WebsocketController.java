package ch.jaunerc.tichu.backend.websocket;

import ch.jaunerc.tichu.backend.domain.game.GrandTichuService;
import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.domain.game.usecase.DealCardsUseCase;
import ch.jaunerc.tichu.backend.domain.game.usecase.PushCardUseCase;
import ch.jaunerc.tichu.backend.domain.game.usecase.ReadyPlayerUseCase;
import ch.jaunerc.tichu.backend.websocket.message.*;
import ch.jaunerc.tichu.backend.websocket.message.game.GameDtoConverter;
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

    private final ReadyPlayerUseCase readyPlayerUseCase;
    private final DealCardsUseCase dealCardsUseCase;
    private final GrandTichuService grandTichuService;
    private final PushCardUseCase pushCardUseCase;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/player-ready-{gameId}")
    @SendTo("/topic/player-ready-{gameId}")
    public ReadyStatusServerMessage playerIsReady(@DestinationVariable("gameId") String gameId) {
        return new ReadyStatusServerMessage(readyPlayerUseCase.updateReadyPlayers(UUID.fromString(gameId)));
    }

    @MessageMapping("/{gameId}/deal-cards/{playerId}")
    @SendTo("/topic/{gameId}/deal-cards/{playerId}")
    public DealCardsServerMessage dealCardsToUser(@DestinationVariable("gameId") String gameId,
                                                  @DestinationVariable("playerId") String playerId) {
        return new DealCardsServerMessage(dealCardsUseCase.dealCards(
                UUID.fromString(gameId),
                UUID.fromString(playerId)));
    }

    @MessageMapping("/{gameId}/grand-tichu/{playerId}")
    @SendTo("/topic/{gameId}/state")
    public GameStateServerMessage grandTichu(@DestinationVariable("gameId") String gameId,
                                              @DestinationVariable("playerId") String playerId,
                                              @Payload GrandTichuPlayerMessage grandTichuPlayerMessage) {
        var updatedGame = grandTichuService.grandTichuByPlayer(
                UUID.fromString(gameId),
                UUID.fromString(playerId),
                grandTichuPlayerMessage.callGrandTichu()
        );
        return new GameStateServerMessage(GameDtoConverter.convert(updatedGame));
    }

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
