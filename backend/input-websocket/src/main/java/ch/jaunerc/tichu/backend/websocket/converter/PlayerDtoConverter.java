package ch.jaunerc.tichu.backend.websocket.converter;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.websocket.api.model.PlayerDto;
import ch.jaunerc.tichu.backend.websocket.api.model.PlayerDto.PlayerSeatIdEnum;
import ch.jaunerc.tichu.backend.websocket.api.model.PlayerDto.TeamIdentifierEnum;
import ch.jaunerc.tichu.backend.websocket.api.model.TichuCalledDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlayerDtoConverter {

    public static PlayerDto convert(Player player, TeamIdentifierEnum teamIdentifier) {
        return new PlayerDto(
                player.user().name(),
                TichuCallDtoConverter.convert(player.tichuCall()),
                TichuCalledDto.NOT_CALLED,
                TichuCalledDto.NOT_CALLED,
                teamIdentifier,
                PlayerSeatIdEnum.fromValue(player.playerSeatId().name())
        );
    }
}
