package ch.jaunerc.tichu.backend.websocket.send;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.port.output.SendGameStateOutputPort;
import ch.jaunerc.tichu.backend.websocket.message.game.GameDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendGameStateWebsocketAdapter implements SendGameStateOutputPort {

    private final MessageSenderService messageSenderService;

    @Override
    public void sendGameState(Game game) {
        messageSenderService.sendGameStateMessage(game.gameId().toString(), GameDtoConverter.convert(game));
    }
}
