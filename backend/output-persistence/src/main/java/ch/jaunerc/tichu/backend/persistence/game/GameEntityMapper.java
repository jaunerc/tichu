package ch.jaunerc.tichu.backend.persistence.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import ch.jaunerc.tichu.backend.persistence.game.team.TeamEntityMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GameEntityMapper {

    static GameEntity map(GamePhase gamePhase) {
        var gameEntity = new GameEntity();
        gameEntity.setGamePhase(gamePhase);
        return gameEntity;
    }

    static GameEntity map(Game game) {
        return new GameEntity(
                game.gameId(),
                game.gamePhase(),
                game.firstTeam() != null ? TeamEntityMapper.map(game.firstTeam()) : null,
                game.secondTeam() != null ? TeamEntityMapper.map(game.secondTeam()) : null
        );
    }

    static Game toDomain(GameEntity gameEntity) {
        var gameBuilder = new Game.Builder(gameEntity.getId(), gameEntity.getGamePhase());

        if (gameEntity.getFirstTeam() != null) {
            gameBuilder.firstTeam(TeamEntityMapper.toDomain(gameEntity.getFirstTeam()));
        }

        if (gameEntity.getSecondTeam() != null) {
            gameBuilder.secondTeam(TeamEntityMapper.toDomain(gameEntity.getSecondTeam()));
        }

        return gameBuilder.build();
    }
}
