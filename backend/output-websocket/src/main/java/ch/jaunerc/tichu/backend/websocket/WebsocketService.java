package ch.jaunerc.tichu.backend.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static ch.jaunerc.tichu.backend.websocket.TopicCreator.createTopicForGameId;

@Service
@RequiredArgsConstructor
public class WebsocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void sendMessage(UUID gameId) {
        simpMessagingTemplate.convertAndSend(createTopicForGameId(gameId), new WebsocketDto(gameId, "test-data")); // TODO use api dto instead of WebsocketDto
    }
}
