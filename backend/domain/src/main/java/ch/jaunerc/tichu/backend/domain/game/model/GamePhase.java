package ch.jaunerc.tichu.backend.domain.game.model;

public enum GamePhase {
    PLAYERS_JOINING,
    PLAYERS_ON_THE_TABLE,
    DEALING_CARDS,
    FIRST_EIGHT_CARDS_ARE_DEALT,
    ALL_CARDS_ARE_DEALT,
    GAME_IS_RUNNING,
    GAME_ENDED
}
