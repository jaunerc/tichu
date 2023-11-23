package ch.jaunerc.tichu.backend.websocket;

import ch.jaunerc.tichu.backend.domain.game.GrandTichuService;
import ch.jaunerc.tichu.backend.domain.game.usecase.DealCardsUseCase;
import ch.jaunerc.tichu.backend.domain.game.usecase.ReadyPlayerUseCase;
import ch.jaunerc.tichu.backend.websocket.message.*;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class WebsocketController {

    private final ReadyPlayerUseCase readyPlayerUseCase;
    private final DealCardsUseCase dealCardsUseCase;
    private final GrandTichuService grandTichuService;

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
    @SendTo("/topic/{gameId}/grand-tichu")
    public GrandTichuServerMessage grandTichu(@DestinationVariable("gameId") String gameId,
                                              @DestinationVariable("playerId") String playerId,
                                              @Payload GrandTichuPlayerMessage grandTichuPlayerMessage) {
        var grandTichuCall = grandTichuService.grandTichuByPlayer(
                UUID.fromString(gameId),
                UUID.fromString(playerId),
                grandTichuPlayerMessage.callGrandTichu()
        );
        return new GrandTichuServerMessage(grandTichuCall.playerNumber(), grandTichuCall.grandTichuCalled());
    }

    @MessageMapping("/{gameId}/small-tichu/{playerId}")
    @SendTo("/topic/{gameId}/small-tichu")
    public void smallTichu(@DestinationVariable("gameId") String gameId,
                           @DestinationVariable("playerId") String playerId) {

    }

    @MessageMapping("/{gameId}/push-card")
    public void pushCard(@DestinationVariable("gameId") String gameId,
                         @Payload PushCardPlayerMessage pushCardPlayerMessage) {
        // if a player pushed all cards -> send cards back to the player
        // response with simpMessageClient
    }
}
