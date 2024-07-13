package ch.jaunerc.tichu.backend.websocket.send;

import ch.jaunerc.tichu.backend.websocket.api.model.GameDto;
import ch.jaunerc.tichu.backend.websocket.api.model.GameStateServerMessageDto;
import ch.jaunerc.tichu.backend.websocket.api.model.PlayerPrivateStateDto;
import ch.jaunerc.tichu.backend.websocket.api.model.PlayerPrivateStateServerMessageDto;
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
            PlayerPrivateStateDto playerPrivateStateDto) {
        simpMessagingTemplate.convertAndSend(
                "/topic/%s/state/%s".formatted(gameId, playerId),
                new PlayerPrivateStateServerMessageDto().playerPrivateState(playerPrivateStateDto));
    }

    public void sendGameStateMessage(
            String gameId,
            GameDto gameDto) {
        simpMessagingTemplate.convertAndSend(
                "/topic/%s/state".formatted(gameId),
                new GameStateServerMessageDto().game(gameDto));
    }
}
