package ch.jaunerc.tichu.backend.web.games;

import ch.jaunerc.tichu.backend.domain.game.usecase.CreateGameUseCase;
import ch.jaunerc.tichu.backend.domain.game.usecase.FindAllGamesUseCase;
import ch.jaunerc.tichu.backend.domain.game.usecase.JoinGameUseCase;
import ch.jaunerc.tichu.backend.web.api.controller.GamesApiDelegate;
import ch.jaunerc.tichu.backend.web.api.model.GameDto;
import ch.jaunerc.tichu.backend.web.api.model.GamesDto;
import ch.jaunerc.tichu.backend.web.api.model.JoinGameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GamesWebAdapter implements GamesApiDelegate {

    private final CreateGameUseCase createGameUseCase;
    private final FindAllGamesUseCase findAllGamesUseCase;
    private final JoinGameUseCase joinGameUseCase;

    @Override
    public ResponseEntity<GameDto> createGame() {
        return ResponseEntity.ok(GameDtoMapper.map(createGameUseCase.createGame()));
    }

    @Override
    public ResponseEntity<GamesDto> getGames() {
        return ResponseEntity.ok(GamesDtoMapper.map(findAllGamesUseCase.findAllGames()));
    }

    @Override
    public ResponseEntity<JoinGameDto> joinGame(String gameId, String userId) {
        return ResponseEntity.ok(JoinGameDtoMapper.map(joinGameUseCase.joinGame(gameId, userId)));
    }
}
