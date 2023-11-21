package ch.jaunerc.tichu.backend.websocket;

import ch.jaunerc.tichu.backend.domain.game.port.ReadyPlayerUseCase;
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

    @MessageMapping("/player-ready-{gameId}")
    @SendTo("/topic/player-ready-{gameId}")
    public ReadyStatusDto playerIsReady(@DestinationVariable("gameId") String gameId, @Payload ReadyRequestDto message) {
        return new ReadyStatusDto(readyPlayerUseCase.updateReadyPlayers(UUID.fromString(gameId)));
    }
}
