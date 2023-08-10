package ch.jaunerc.tichu.backend.web.games;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.web.api.model.GamesDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GamesDtoMapper {

    static GamesDto map(List<Game> games) {
        return new GamesDto().games(
                games.stream()
                        .map(GameDtoMapper::map)
                        .toList());
    }
}
