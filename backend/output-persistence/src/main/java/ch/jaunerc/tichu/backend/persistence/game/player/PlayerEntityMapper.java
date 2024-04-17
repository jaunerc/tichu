package ch.jaunerc.tichu.backend.persistence.game.player;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.persistence.user.UserEntityMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerEntityMapper {

    public static PlayerEntity map(Player player) {
        var playerEntity =  new PlayerEntity();

        if (player.uuid() != null) {
            playerEntity.setId(player.uuid());
        }

        if (player.pushedCards() != null) {
            playerEntity.setPushedCards(player.pushedCards());
        }

        if (player.receivedCards() != null) {
            playerEntity.setReceivedCards(player.receivedCards());
        }

        playerEntity.setPlayerSeatId(player.playerSeatId());
        playerEntity.setFirstEightCardsReceived(player.firstEightCardsReceived());
        playerEntity.setGrandTichuCalled(player.grandTichuCalled());
        playerEntity.setSmallTichuCalled(player.smallTichuCalled());
        playerEntity.setUser(UserEntityMapper.map(player.user()));
        playerEntity.setCards(player.cards());
        return playerEntity;
    }

    public static Player toDomain(PlayerEntity playerEntity) {
        return new Player.Builder(playerEntity.getId())
                .user(UserEntityMapper.toDomain(playerEntity.getUser()))
                .playerSeatId(playerEntity.getPlayerSeatId())
                .firstEightCardsReceived(playerEntity.isFirstEightCardsReceived())
                .grandTichuCalled(playerEntity.isGrandTichuCalled())
                .smallTichuCalled(playerEntity.isSmallTichuCalled())
                .cards(playerEntity.getCards())
                .pushedCards(playerEntity.getPushedCards())
                .receivedCards(playerEntity.getReceivedCards())
                .build();
    }
}
