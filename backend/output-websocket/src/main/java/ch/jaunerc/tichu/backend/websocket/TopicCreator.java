package ch.jaunerc.tichu.backend.websocket;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TopicCreator {
    private static final String TOPIC_PREFIX = "/topic/game-update";

    static String createTopicForGameId(UUID gameId) {
        return String.format("%s-%s", TOPIC_PREFIX, gameId.toString());
    }
}
