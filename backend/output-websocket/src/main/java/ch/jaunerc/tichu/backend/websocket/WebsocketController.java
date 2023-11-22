package ch.jaunerc.tichu.backend.websocket;

import ch.jaunerc.tichu.backend.domain.game.usecase.DealCardsUseCase;
import ch.jaunerc.tichu.backend.domain.game.usecase.ReadyPlayerUseCase;
import ch.jaunerc.tichu.backend.websocket.message.DealCardsMessage;
import ch.jaunerc.tichu.backend.websocket.message.PushCardToPlayerMessage;
import ch.jaunerc.tichu.backend.websocket.message.ReadyStatusMessage;
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

    @MessageMapping("/player-ready-{gameId}")
    @SendTo("/topic/player-ready-{gameId}")
    public ReadyStatusMessage playerIsReady(@DestinationVariable("gameId") String gameId, @Payload ReadyRequestDto message) {
        return new ReadyStatusMessage(readyPlayerUseCase.updateReadyPlayers(UUID.fromString(gameId)));
    }

    @MessageMapping("/{gameId}/deal-cards/{playerId}")
    @SendTo("/topic/{gameId}/deal-cards/{playerId}")
    public DealCardsMessage dealCardsToUser(@DestinationVariable("gameId") String gameId,
                                            @DestinationVariable("playerId") String playerId) {
        return new DealCardsMessage(dealCardsUseCase.dealCards(
                UUID.fromString(gameId),
                UUID.fromString(playerId)));
    }
}
