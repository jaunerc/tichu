package ch.jaunerc.tichu.backend.websocket.converter;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.websocket.api.model.PlayerDto;
import ch.jaunerc.tichu.backend.websocket.api.model.PlayerDto.PlayerSeatIdEnum;
import ch.jaunerc.tichu.backend.websocket.api.model.PlayerDto.TeamIdentifierEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlayerDtoConverter {

    public static PlayerDto convert(Player player, TeamIdentifierEnum teamIdentifier) {
        var playerDto = new PlayerDto();
        playerDto.setName(player.user().name());
        playerDto.setGrandTichuCalled(player.grandTichuCalled());
        playerDto.setSmallTichuCalled(player.smallTichuCalled());
        playerDto.setPlayerSeatId(PlayerSeatIdEnum.fromValue(player.playerSeatId().name()));
        playerDto.setTeamIdentifier(teamIdentifier);
        return playerDto;
    }
}
