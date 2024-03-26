package ch.jaunerc.tichu.backend.domain.game.model;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.domain.user.model.User;

import java.util.List;
import java.util.UUID;

public record Player(
        UUID uuid,
        User user,
        PlayerSeatId playerSeatId,
        boolean grandTichuCalled,
        boolean smallTichuCalled,
        boolean firstEightCardsReceived,
        List<Card> pushedCards,

        List<Card> receivedCards,
        List<Card> cards) {

    public final static class Builder {
        private final UUID playerId;
        private User user;
        private PlayerSeatId playerSeatId;
        private boolean grandTichuCalled;
        private boolean smallTichuCalled;
        private boolean firstEightCardsReceived;
        private List<Card> pushedCards;
        private List<Card> receivedCards;
        private List<Card> cards;

        public Builder(UUID playerId) {
            this.playerId = playerId;
        }

        public static Builder of(Player player) {
            return new Builder(player.uuid)
                    .user(player.user)
                    .grandTichuCalled(player.grandTichuCalled)
                    .smallTichuCalled(player.smallTichuCalled)
                    .firstEightCardsReceived(player.firstEightCardsReceived)
                    .pushedCards(player.pushedCards)
                    .receivedCards(player.receivedCards)
                    .cards(player.cards);
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder playerSeatId(PlayerSeatId playerSeatId) {
            this.playerSeatId = playerSeatId;
            return this;
        }

        public Builder grandTichuCalled(boolean grandTichuCalled) {
            this.grandTichuCalled = grandTichuCalled;
            return this;
        }

        public Builder smallTichuCalled(boolean smallTichuCalled) {
            this.smallTichuCalled = smallTichuCalled;
            return this;
        }

        public Builder firstEightCardsReceived(boolean firstEightCardsReceived) {
            this.firstEightCardsReceived = firstEightCardsReceived;
            return this;
        }

        public Builder pushedCards(List<Card> pushedCards) {
            this.pushedCards = pushedCards;
            return this;
        }

        public Builder receivedCards(List<Card> receivedCards) {
            this.receivedCards = receivedCards;
            return this;
        }

        public Builder cards(List<Card> cards) {
            this.cards = cards;
            return this;
        }

        public Player build() {
            return new Player(
                    this.playerId,
                    this.user,
                    this.playerSeatId,
                    this.grandTichuCalled,
                    this.smallTichuCalled,
                    this.firstEightCardsReceived,
                    this.pushedCards,
                    this.receivedCards,
                    this.cards
            );
        }
    }
}
