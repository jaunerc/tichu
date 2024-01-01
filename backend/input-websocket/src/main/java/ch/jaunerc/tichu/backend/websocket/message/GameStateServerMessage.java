package ch.jaunerc.tichu.backend.websocket.message;

import ch.jaunerc.tichu.backend.websocket.message.game.GameDto;

public record GameStateServerMessage(GameDto game) {
}
