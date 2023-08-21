package ch.jaunerc.tichu.backend.persistence.game.player;

import ch.jaunerc.tichu.backend.domain.game.model.GamePlayer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerEntityMapper {

    public static PlayerEntity map(GamePlayer player) {
        var playerEntity =  new PlayerEntity();
        playerEntity.setCards(player.cards());
        return playerEntity;
    }

    public static GamePlayer toDomain(PlayerEntity playerEntity) {
        return new GamePlayer(
                playerEntity.getId(),
                playerEntity.getCards()
        );
    }
}
