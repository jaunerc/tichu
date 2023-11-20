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

        playerEntity.setUser(UserEntityMapper.map(player.user()));
        playerEntity.setCards(player.cards());
        return playerEntity;
    }

    public static Player toDomain(PlayerEntity playerEntity) {
        return new Player(
                playerEntity.getId(),
                UserEntityMapper.toDomain(playerEntity.getUser()),
                playerEntity.getCards()
        );
    }
}
