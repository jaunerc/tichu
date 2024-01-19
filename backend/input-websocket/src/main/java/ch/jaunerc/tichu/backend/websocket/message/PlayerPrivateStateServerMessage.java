package ch.jaunerc.tichu.backend.websocket.message;

import ch.jaunerc.tichu.backend.websocket.message.game.PlayerPrivateDto;

public record PlayerPrivateStateServerMessage(PlayerPrivateDto playerPrivateState) {
}
