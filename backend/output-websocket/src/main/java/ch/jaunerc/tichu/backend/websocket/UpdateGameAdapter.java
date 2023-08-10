package ch.jaunerc.tichu.backend.websocket;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.port.UpdateGamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateGameAdapter implements UpdateGamePort {

    private final WebsocketService websocketService;

    @Override
    public void sendGameUpdate(Game game) {
        websocketService.sendMessage(game.gameId());
    }
}
