package ch.jaunerc.tichu.backend.websocket.message.game;

public record PlayerDto(
        String name,
        boolean grandTichuCalled,
        boolean smallTichuCalled,
        TeamIdentifierDto teamIdentifierDto) {
}
