package ch.jaunerc.tichu.backend.websocket.message;

public record PushCardPlayerMessage(String cardName, int recipientPlayerNumber) {
}
