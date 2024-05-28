package ch.jaunerc.tichu.backend.websocket.send;

import ch.jaunerc.tichu.backend.websocket.message.GameStateServerMessage;
import ch.jaunerc.tichu.backend.websocket.message.PlayerPrivateStateServerMessage;
import ch.jaunerc.tichu.backend.websocket.message.game.GameDto;
import ch.jaunerc.tichu.backend.websocket.message.game.PlayerPrivateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSenderService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void sendPlayerPrivateStateMessage(
            String gameId,
            String playerId,
            PlayerPrivateDto playerPrivateDto) {
        simpMessagingTemplate.convertAndSend(
                "/topic/%s/state/%s".formatted(gameId, playerId),
                new PlayerPrivateStateServerMessage(playerPrivateDto));
    }

    public void sendGameStateMessage(
            String gameId,
            GameDto gameDto) {
        simpMessagingTemplate.convertAndSend(
                "/topic/%s/state".formatted(gameId),
                new GameStateServerMessage(gameDto));
    }
}
