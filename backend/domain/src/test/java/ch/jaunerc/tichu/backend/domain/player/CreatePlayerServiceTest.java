package ch.jaunerc.tichu.backend.domain.player;

import ch.jaunerc.tichu.backend.domain.player.model.Player;
import ch.jaunerc.tichu.backend.domain.player.port.CreatePlayerPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePlayerServiceTest {

    @Mock
    private CreatePlayerPort createPlayerPort;
    @InjectMocks
    private CreatePlayerService createPlayerService;

    @Test
    void createPlayer() {
        var playerName = "The chosen one";
        when(createPlayerPort.createPlayer(anyString())).thenReturn(new Player(UUID.randomUUID(), playerName));

        var player = createPlayerService.createPlayer(playerName);

        assertThat(player.id()).isNotNull();
        assertThat(player.name()).isEqualTo(playerName);
    }
}