package ch.jaunerc.tichu.backend.websocket;

import ch.jaunerc.tichu.backend.domain.game.CountReadyPlayersService;
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

    private final CountReadyPlayersService countReadyPlayersService;

    @MessageMapping("/ready-players-{gameId}")
    @SendTo("/topic/ready-players-{gameId}")
    public ReadyStatusDto readyPlayersInGame(@DestinationVariable("gameId") String gameId, @Payload ReadyRequestDto message) {
        return new ReadyStatusDto(countReadyPlayersService.countReadyPlayers(UUID.fromString(gameId)));
    }
}