package ch.jaunerc.tichu.backend.domain.game.model;

import java.util.Arrays;
import java.util.Optional;

public enum PlayerSeatId {
    FIRST, SECOND, THIRD, FOURTH;

    public static Optional<PlayerSeatId> getByIndex(int index) {
        return Arrays.stream(PlayerSeatId.values())
                .skip(index)
                .findFirst();
    }
}
