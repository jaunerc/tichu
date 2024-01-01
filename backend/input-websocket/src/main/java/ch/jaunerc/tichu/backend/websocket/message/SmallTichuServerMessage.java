package ch.jaunerc.tichu.backend.websocket.message;

public record SmallTichuServerMessage(int playerNumber, boolean smallTichuCalled) {
}
