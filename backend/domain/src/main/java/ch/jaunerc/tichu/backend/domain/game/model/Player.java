package ch.jaunerc.tichu.backend.domain.game.model;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;

import java.util.List;
import java.util.UUID;

public record Player(UUID uuid, List<Card> cards) {
}