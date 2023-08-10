package ch.jaunerc.tichu.backend.web.games;

import ch.jaunerc.tichu.backend.domain.game.port.CreateGameUseCase;
import ch.jaunerc.tichu.backend.domain.game.port.FindAllGamesUseCase;
import ch.jaunerc.tichu.backend.web.api.controller.GamesApiDelegate;
import ch.jaunerc.tichu.backend.web.api.model.GameDto;
import ch.jaunerc.tichu.backend.web.api.model.GamesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GamesWebAdapter implements GamesApiDelegate {

    private final CreateGameUseCase createGameUseCase;
    private final FindAllGamesUseCase findAllGamesUseCase;

    @Override
    public ResponseEntity<GameDto> createGame() {
        return ResponseEntity.ok(GameDtoMapper.map(createGameUseCase.createGame()));
    }

    @Override
    public ResponseEntity<GamesDto> getGames() {
        return ResponseEntity.ok(GamesDtoMapper.map(findAllGamesUseCase.findAllGames()));
    }
}
