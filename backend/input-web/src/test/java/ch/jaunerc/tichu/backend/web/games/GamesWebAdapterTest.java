package ch.jaunerc.tichu.backend.web.games;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.usecase.CreateGameUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GamesWebAdapterTest {

    @Mock
    private CreateGameUseCase createGameUseCase;
    @InjectMocks
    private GamesWebAdapter gamesWebAdapter;

    @Test
    void createGame() {
        var uuid = "00000000-0000-0000-0000-000000000000";
        when(createGameUseCase.createGame())
                .thenReturn(new Game(UUID.fromString(uuid), null, null, null));

        // act
        var responseEntity = gamesWebAdapter.createGame();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getId()).isEqualTo(uuid);
    }
}