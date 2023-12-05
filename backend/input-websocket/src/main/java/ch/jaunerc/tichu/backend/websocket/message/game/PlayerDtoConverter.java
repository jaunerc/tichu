package ch.jaunerc.tichu.backend.websocket.message.game;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlayerDtoConverter {

    public static PlayerDto convert(Player player, TeamIdentifierDto teamIdentifierDto) {
        return new PlayerDto(
                player.grandTichuCalled(),
                player.smallTichuCalled(),
                teamIdentifierDto
        );
    }
}
