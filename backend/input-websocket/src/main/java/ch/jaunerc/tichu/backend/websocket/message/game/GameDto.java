package ch.jaunerc.tichu.backend.websocket.message.game;

import java.util.List;

public record GameDto(
        List<PlayerDto> players,
        GamePhaseDto gamePhase) {
}
