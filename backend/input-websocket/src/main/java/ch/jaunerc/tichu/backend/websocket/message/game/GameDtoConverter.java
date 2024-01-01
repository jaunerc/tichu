package ch.jaunerc.tichu.backend.websocket.message.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

import static ch.jaunerc.tichu.backend.websocket.message.game.TeamIdentifierDto.FIRST_TEAM;
import static ch.jaunerc.tichu.backend.websocket.message.game.TeamIdentifierDto.SECOND_TEAM;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class GameDtoConverter {

    public static GameDto convert(Game game) {
        var players = Stream.concat(convertFirstTeamPlayers(game), convertSecondTeamPlayers(game)).toList();
        return new GameDto(players, GamePhaseDtoConverter.convert(game.gamePhase()));
    }

    private static Stream<PlayerDto> convertFirstTeamPlayers(Game game) {
        return Stream.of(
                PlayerDtoConverter.convert(game.firstTeam().firstPlayer(), FIRST_TEAM),
                PlayerDtoConverter.convert(game.firstTeam().secondPlayer(), FIRST_TEAM)
        );
    }

    private static Stream<PlayerDto> convertSecondTeamPlayers(Game game) {
        return Stream.of(
                PlayerDtoConverter.convert(game.secondTeam().firstPlayer(), SECOND_TEAM),
                PlayerDtoConverter.convert(game.secondTeam().secondPlayer(), SECOND_TEAM)
        );
    }
}
