package ch.jaunerc.tichu.backend.websocket.message;

import java.util.List;

public record PushCardsServerMessage(List<String> cardNames) {
}
