package ch.jaunerc.tichu.backend.web.games;

import ch.jaunerc.tichu.backend.domain.game.model.JoinGame;
import ch.jaunerc.tichu.backend.web.api.model.JoinGameDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JoinGameDtoMapper {

    static JoinGameDto map(JoinGame joinGame) {
        return new JoinGameDto(
                joinGame.gameId().toString(),
                joinGame.playerId().toString(),
                JoinGameDto.PlayerSeatIdEnum.fromValue(joinGame.playerSeatId().toString()));
    }
}
