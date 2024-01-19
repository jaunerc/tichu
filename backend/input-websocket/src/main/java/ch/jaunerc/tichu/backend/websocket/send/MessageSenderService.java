package ch.jaunerc.tichu.backend.websocket.send;

import ch.jaunerc.tichu.backend.websocket.message.PlayerPrivateStateServerMessage;
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
                "/topic/" + gameId + "/state/" + playerId,
                new PlayerPrivateStateServerMessage(playerPrivateDto));
    }
}
