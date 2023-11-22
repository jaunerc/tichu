package ch.jaunerc.tichu.backend.websocket.message;

public record GrandTichuServerMessage(int playerNumber, boolean grandTichuCalled) {
}
