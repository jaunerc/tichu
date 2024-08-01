package ch.jaunerc.tichu.backend.websocket.controller;


import ch.jaunerc.tichu.backend.domain.game.port.input.TichuCallInputPort;
import ch.jaunerc.tichu.backend.websocket.api.model.GameStateServerMessageDto;
import ch.jaunerc.tichu.backend.websocket.api.model.TichuCallPlayerMessageDto;
import ch.jaunerc.tichu.backend.websocket.converter.GameDtoConverter;
import ch.jaunerc.tichu.backend.websocket.converter.TichuCallDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class TichuCallWebsocketController {

    private final TichuCallInputPort tichuCallInputPort;

    @MessageMapping("/{gameId}/small-tichu/{playerId}")
    @SendTo("/topic/{gameId}/state")
    public GameStateServerMessageDto tichuCalled(@DestinationVariable("gameId") String gameId,
                                                 @DestinationVariable("playerId") String playerId,
                                                 @Payload TichuCallPlayerMessageDto tichuCallPlayerMessageDto) {
        var updatedGame = tichuCallInputPort.tichuCallByPlayer(
                UUID.fromString(gameId),
                UUID.fromString(playerId),
                TichuCallDtoConverter.toDomain(tichuCallPlayerMessageDto.getTichuCall())
        );

        return new GameStateServerMessageDto(GameDtoConverter.convert(updatedGame));
    }
}
