package ch.jaunerc.tichu.backend.websocket.controller;

import ch.jaunerc.tichu.backend.domain.game.GrandTichuService;
import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.domain.game.usecase.DealCardsUseCase;
import ch.jaunerc.tichu.backend.websocket.message.GameStateServerMessage;
import ch.jaunerc.tichu.backend.websocket.message.GrandTichuPlayerMessage;
import ch.jaunerc.tichu.backend.websocket.message.PlayerPrivateStateServerMessage;
import ch.jaunerc.tichu.backend.websocket.message.game.GameDtoConverter;
import ch.jaunerc.tichu.backend.websocket.message.game.PlayerPrivateDtoConverter;
import ch.jaunerc.tichu.backend.websocket.send.MessageSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class DealCardsWebsocketController {

    private final DealCardsUseCase dealCardsUseCase;
    private final GrandTichuService grandTichuService;
    private final MessageSenderService messageSenderService;

    @MessageMapping("/{gameId}/deal-cards/{playerId}")
    @SendTo("/topic/{gameId}/state/{playerId}")
    public PlayerPrivateStateServerMessage dealCardsToUser(@DestinationVariable("gameId") String gameId,
                                                           @DestinationVariable("playerId") String playerId) {
        var playerPrivateDto = PlayerPrivateDtoConverter.convert(dealCards(gameId, playerId));

        return new PlayerPrivateStateServerMessage(playerPrivateDto);
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

        messageSenderService.sendPlayerPrivateStateMessage(gameId, playerId,
                PlayerPrivateDtoConverter.convert(dealCards(gameId, playerId)));

        return new GameStateServerMessage(GameDtoConverter.convert(updatedGame));
    }

    private List<Card> dealCards(String gameId, String playerId) {
        return dealCardsUseCase.dealCards(UUID.fromString(gameId), UUID.fromString(playerId));
    }
}
