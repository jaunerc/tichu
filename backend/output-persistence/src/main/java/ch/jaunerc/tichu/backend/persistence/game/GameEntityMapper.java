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

    static Game toDomain(GameEntity gameEntity) {
        return new Game(
                gameEntity.getId(),
                TeamEntityMapper.toDomain(gameEntity.getFirstTeam()),
                TeamEntityMapper.toDomain(gameEntity.getSecondTeam()),
                gameEntity.getGamePhase());
    }
}
