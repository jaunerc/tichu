package ch.jaunerc.tichu.backend.domain.player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CreatePlayerServiceTest {

    @InjectMocks
    private CreatePlayerService createPlayerService;

    @Test
    void createPlayer() {
        var playerName = "The chosen one";
        var player = createPlayerService.createPlayer(playerName);

        assertThat(player.id()).isNotNull();
        assertThat(player.name()).isEqualTo(playerName);
    }
}