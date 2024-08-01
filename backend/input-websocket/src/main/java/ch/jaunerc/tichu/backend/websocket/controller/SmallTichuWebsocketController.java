package ch.jaunerc.tichu.backend.websocket.controller;

import ch.jaunerc.tichu.backend.domain.game.port.input.TichuCallInputPort;
import ch.jaunerc.tichu.backend.websocket.api.model.GameStateServerMessageDto;
import ch.jaunerc.tichu.backend.websocket.api.model.SmallTichuPlayerMessageDto;
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
public class SmallTichuWebsocketController {

    private final TichuCallInputPort tichuCallInputPort;

    @MessageMapping("/{gameId}/small-tichu/{playerId}")
    @SendTo("/topic/{gameId}/state")
    public GameStateServerMessageDto smallTichu(@DestinationVariable("gameId") String gameId,
                                                @DestinationVariable("playerId") String playerId,
                                                @Payload SmallTichuPlayerMessageDto smallTichuPlayerMessageDto) {
        var updatedGame = tichuCallInputPort.smallTichuByPlayer(
                UUID.fromString(gameId),
                UUID.fromString(playerId),
                smallTichuPlayerMessageDto.getCallSmallTichu());

        return new GameStateServerMessageDto(GameDtoConverter.convert(updatedGame));
    }
}
