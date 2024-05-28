package ch.jaunerc.tichu.backend.websocket.controller;

import ch.jaunerc.tichu.backend.domain.game.port.input.GrandTichuInputPort;
import ch.jaunerc.tichu.backend.websocket.message.GameStateServerMessage;
import ch.jaunerc.tichu.backend.websocket.message.GrandTichuPlayerMessage;
import ch.jaunerc.tichu.backend.websocket.message.game.GameDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class GrandTichuWebsocketController {

    private final GrandTichuInputPort grandTichuInputPort;

    @MessageMapping("/{gameId}/grand-tichu/{playerId}")
    @SendTo("/topic/{gameId}/state")
    public GameStateServerMessage grandTichu(@DestinationVariable("gameId") String gameId,
                                             @DestinationVariable("playerId") String playerId,
                                             @Payload GrandTichuPlayerMessage grandTichuPlayerMessage) {
        var updatedGame = grandTichuInputPort.grandTichuByPlayer(
                UUID.fromString(gameId),
                UUID.fromString(playerId),
                grandTichuPlayerMessage.callGrandTichu()
        );

        return new GameStateServerMessage(GameDtoConverter.convert(updatedGame));
    }
}
