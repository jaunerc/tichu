package ch.jaunerc.tichu.backend.websocket.message.game;

import java.util.List;

public record PlayerPrivateDto(
        List<String> cards,
        List<String> receivedCards) {
}
