package ch.jaunerc.tichu.backend.websocket.send;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.port.output.SendPlayerPrivateStateOutputPort;
import ch.jaunerc.tichu.backend.websocket.converter.PlayerPrivateDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SendPlayerPrivateStateWebsocketAdapter implements SendPlayerPrivateStateOutputPort {

    private final MessageSenderService messageSenderService;

    @Override
    public void sendPlayerPrivateState(UUID gameId, Player player) {
        messageSenderService.sendPlayerPrivateStateMessage(
                gameId.toString(),
                player.uuid().toString(),
                PlayerPrivateDtoConverter.convert(player.cards()));
    }
}
