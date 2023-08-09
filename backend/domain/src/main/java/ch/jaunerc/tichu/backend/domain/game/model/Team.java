package ch.jaunerc.tichu.backend.domain.game.model;

public record Team(GamePlayer firstPlayer, GamePlayer secondPlayer, int points) {
}
