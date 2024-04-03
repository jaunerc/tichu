package ch.jaunerc.tichu.backend.websocket.message.game;

import ch.jaunerc.tichu.backend.domain.game.model.PlayerSeatId;

public record PlayerDto(
        String name,
        boolean grandTichuCalled,
        boolean smallTichuCalled,
        PlayerSeatId playerSeatId,
        TeamIdentifierDto teamIdentifierDto) {
}
