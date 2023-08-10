package ch.jaunerc.tichu.backend.websocket;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class TopicCreatorTest {

    @Test
    void createTopicForGameId() {
        var uuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
        var result = TopicCreator.createTopicForGameId(uuid);

        assertThat(result).isEqualTo("/topic/game-update-00000000-0000-0000-0000-000000000000");
    }
}