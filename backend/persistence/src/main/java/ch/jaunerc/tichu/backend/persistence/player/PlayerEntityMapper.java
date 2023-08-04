package ch.jaunerc.tichu.backend.persistence.player;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerEntityMapper {

    static PlayerEntity map (String playerName) {
        var playerEntity = new PlayerEntity();
        playerEntity.setName(playerName);
        return playerEntity;
    }
}
