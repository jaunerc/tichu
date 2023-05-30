package ch.jaunerc.tichu.backend.web;

import ch.jaunerc.tichu.backend.domain.player.model.Player;
import ch.jaunerc.tichu.backend.domain.player.port.CreatePlayerUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayersWebAdapterTest {

    @Mock
    private CreatePlayerUseCase createPlayerUseCase;

    @InjectMocks
    private PlayersWebAdapter playersWebAdapter;

    @Test
    void createPlayer() {
        var uuid = "00000000-0000-0000-0000-000000000000";
        var playerName = "The captain";
        when(createPlayerUseCase.createPlayer(anyString()))
                .thenReturn(new Player(UUID.fromString(uuid), playerName));

        // act
        var responseEntity = playersWebAdapter.createPlayer(playerName);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getId()).isEqualTo(uuid);
        assertThat(responseEntity.getBody().getName()).isEqualTo(playerName);
    }
}