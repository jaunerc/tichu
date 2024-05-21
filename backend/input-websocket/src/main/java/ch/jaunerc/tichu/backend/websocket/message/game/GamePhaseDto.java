package ch.jaunerc.tichu.backend.websocket.message.game;

public enum GamePhaseDto {
    PLAYERS_JOINING,
    PLAYERS_ON_THE_TABLE,
    DEALING_CARDS,
    FIRST_EIGHT_CARDS_ARE_DEALT,
    ALL_CARDS_ARE_DEALT,
    GAME_IS_RUNNING,
    GAME_ENDED
}
