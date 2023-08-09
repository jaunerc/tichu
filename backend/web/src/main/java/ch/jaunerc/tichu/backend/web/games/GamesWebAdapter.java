package ch.jaunerc.tichu.backend.web.games;

import ch.jaunerc.tichu.backend.domain.game.port.CreateGameUseCase;
import ch.jaunerc.tichu.backend.web.api.controller.GamesApiDelegate;
import ch.jaunerc.tichu.backend.web.api.model.GameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GamesWebAdapter implements GamesApiDelegate {

    private final CreateGameUseCase createGameUseCase;

    @Override
    public ResponseEntity<GameDto> createGame() {
        return ResponseEntity.ok(GameDtoMapper.map(createGameUseCase.createGame()));
    }
}
