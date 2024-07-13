package ch.jaunerc.tichu.backend.websocket.controller;

import ch.jaunerc.tichu.backend.domain.game.port.input.GrandTichuInputPort;
import ch.jaunerc.tichu.backend.websocket.api.model.GameStateServerMessageDto;
import ch.jaunerc.tichu.backend.websocket.api.model.GrandTichuPlayerMessageDto;
import ch.jaunerc.tichu.backend.websocket.converter.GameDtoConverter;
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
    public GameStateServerMessageDto grandTichu(@DestinationVariable("gameId") String gameId,
                                                @DestinationVariable("playerId") String playerId,
                                                @Payload GrandTichuPlayerMessageDto grandTichuPlayerMessage) {
        var updatedGame = grandTichuInputPort.grandTichuByPlayer(
                UUID.fromString(gameId),
                UUID.fromString(playerId),
                grandTichuPlayerMessage.getCallGrandTichu()
        );

        return new GameStateServerMessageDto().game(GameDtoConverter.convert(updatedGame));
    }
}
