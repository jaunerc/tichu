package ch.jaunerc.tichu.backend.websocket.message.game;

public record PlayerDto(
        boolean grandTichuCalled,
        boolean smallTichuCalled,
        TeamIdentifierDto teamIdentifierDto) {
}
