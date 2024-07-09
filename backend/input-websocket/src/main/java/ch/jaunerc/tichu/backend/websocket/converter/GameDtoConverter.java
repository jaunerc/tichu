package ch.jaunerc.tichu.backend.websocket.converter;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.websocket.api.model.GameDto;
import ch.jaunerc.tichu.backend.websocket.api.model.PlayerDto;
import ch.jaunerc.tichu.backend.websocket.api.model.PlayerDto.TeamIdentifierEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class GameDtoConverter {

    public static ch.jaunerc.tichu.backend.websocket.api.model.GameDto convert(Game game) {
        var players = Stream.concat(convertFirstTeamPlayers(game), convertSecondTeamPlayers(game)).toList();
        return new GameDto(players, GameDto.GamePhaseEnum.fromValue(game.gamePhase().name()));
    }

    private static Stream<ch.jaunerc.tichu.backend.websocket.api.model.PlayerDto> convertFirstTeamPlayers(Game game) {
        return Stream.of(
                PlayerDtoConverter.convert(game.firstTeam().firstPlayer(), TeamIdentifierEnum.FIRST_TEAM),
                PlayerDtoConverter.convert(game.firstTeam().secondPlayer(), TeamIdentifierEnum.FIRST_TEAM)
        );
    }

    private static Stream<PlayerDto> convertSecondTeamPlayers(Game game) {
        return Stream.of(
                PlayerDtoConverter.convert(game.secondTeam().firstPlayer(), TeamIdentifierEnum.SECOND_TEAM),
                PlayerDtoConverter.convert(game.secondTeam().secondPlayer(), TeamIdentifierEnum.SECOND_TEAM)
        );
    }
}
