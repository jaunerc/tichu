package ch.jaunerc.tichu.backend.domain.game.model;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.domain.user.model.User;

import java.util.List;
import java.util.UUID;

public record Player(
        UUID uuid,
        User user,
        PlayerSeatId playerSeatId,
        TichuCall tichuCall,
        boolean firstEightCardsReceived,
        List<Card> pushedCards,

        List<Card> receivedCards,
        List<Card> cards) {

    public final static class Builder {
        private final UUID playerId;
        private User user;
        private PlayerSeatId playerSeatId;
        private TichuCall tichuCall;
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
                    .tichuCall(player.tichuCall)
                    .firstEightCardsReceived(player.firstEightCardsReceived)
                    .playerSeatId(player.playerSeatId)
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

        public Builder tichuCall(TichuCall tichuCall) {
            this.tichuCall = tichuCall;
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
                    this.tichuCall,
                    this.firstEightCardsReceived,
                    this.pushedCards,
                    this.receivedCards,
                    this.cards
            );
        }
    }
}
