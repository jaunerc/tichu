package ch.jaunerc.tichu.backend.web.games;

import ch.jaunerc.tichu.backend.domain.game.port.input.CreateGameInputPort;
import ch.jaunerc.tichu.backend.domain.game.port.input.FindAllGamesInputPort;
import ch.jaunerc.tichu.backend.domain.game.port.input.JoinGameInputPort;
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

    private final CreateGameInputPort createGameInputPort;
    private final FindAllGamesInputPort findAllGamesInputPort;
    private final JoinGameInputPort joinGameInputPort;

    @Override
    public ResponseEntity<GameDto> createGame() {
        return ResponseEntity.ok(GameDtoMapper.map(createGameInputPort.createGame()));
    }

    @Override
    public ResponseEntity<GamesDto> getGames() {
        return ResponseEntity.ok(GamesDtoMapper.map(findAllGamesInputPort.findAllGames()));
    }

    @Override
    public ResponseEntity<JoinGameDto> joinGame(String gameId, String userId) {
        return ResponseEntity.ok(JoinGameDtoMapper.map(joinGameInputPort.joinGame(gameId, userId)));
    }
}
