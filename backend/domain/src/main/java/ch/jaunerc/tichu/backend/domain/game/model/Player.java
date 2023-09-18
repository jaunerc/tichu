package ch.jaunerc.tichu.backend.domain.game.model;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.domain.user.model.User;

import java.util.List;
import java.util.UUID;

public record Player(UUID uuid, User user, List<Card> cards) {
}
