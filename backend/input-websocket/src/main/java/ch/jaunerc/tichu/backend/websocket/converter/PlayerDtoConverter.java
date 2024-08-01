package ch.jaunerc.tichu.backend.websocket.converter;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.TichuCalled;
import ch.jaunerc.tichu.backend.websocket.api.model.PlayerDto;
import ch.jaunerc.tichu.backend.websocket.api.model.PlayerDto.PlayerSeatIdEnum;
import ch.jaunerc.tichu.backend.websocket.api.model.PlayerDto.TeamIdentifierEnum;
import ch.jaunerc.tichu.backend.websocket.api.model.TichuCalledDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlayerDtoConverter {

    public static PlayerDto convert(Player player, TeamIdentifierEnum teamIdentifier) {
        var playerDto = new PlayerDto();
        playerDto.setName(player.user().name());
        playerDto.setGrandTichuCalled(convertTichuCall(player.grandTichuCalled()));
        playerDto.setSmallTichuCalled(convertTichuCall(player.smallTichuCalled()));
        playerDto.setPlayerSeatId(PlayerSeatIdEnum.fromValue(player.playerSeatId().name()));
        playerDto.setTeamIdentifier(teamIdentifier);
        return playerDto;
    }

    private static TichuCalledDto convertTichuCall(TichuCalled tichuCalled) {
        return tichuCalled != null ? TichuCalledDto.fromValue(tichuCalled.name()) : TichuCalledDto.NOT_ANSWERED;
    }
}
