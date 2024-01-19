package ch.jaunerc.tichu.backend.websocket.send;

import ch.jaunerc.tichu.backend.websocket.message.PlayerPrivateStateServerMessage;
import ch.jaunerc.tichu.backend.websocket.message.game.PlayerPrivateDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessageSenderServiceTest {

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @InjectMocks
    private MessageSenderService messageSenderService;

    @Test
    void setMessageSenderService() {
        // given
        PlayerPrivateDto playerPrivateDto = new PlayerPrivateDto(List.of(), List.of());

        // when
        messageSenderService.sendPlayerPrivateStateMessage("gameId", "playerId", playerPrivateDto);

        // then
        verify(simpMessagingTemplate).convertAndSend(
                anyString(),
                any(PlayerPrivateStateServerMessage.class));
    }
}