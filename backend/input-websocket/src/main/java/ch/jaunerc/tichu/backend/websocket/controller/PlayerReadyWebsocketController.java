package ch.jaunerc.tichu.backend.websocket.controller;

import ch.jaunerc.tichu.backend.domain.game.usecase.ReadyPlayerUseCase;
import ch.jaunerc.tichu.backend.websocket.message.ReadyStatusServerMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PlayerReadyWebsocketController {

    private final ReadyPlayerUseCase readyPlayerUseCase;

    @MessageMapping("/player-ready-{gameId}")
    @SendTo("/topic/player-ready-{gameId}")
    public ReadyStatusServerMessage playerIsReady(@DestinationVariable("gameId") String gameId) {
        return new ReadyStatusServerMessage(readyPlayerUseCase.updateReadyPlayers(UUID.fromString(gameId)));
    }
}
