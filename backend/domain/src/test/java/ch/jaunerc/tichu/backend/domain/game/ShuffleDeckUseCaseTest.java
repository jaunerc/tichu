package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindGameByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.SavePlayerOutputPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShuffleDeckUseCaseTest {

    @Mock
    private FindGameByIdOutputPort findGameByIdPort;
    @Mock
    private SavePlayerOutputPort savePlayerPort;
    @InjectMocks
    private ShuffleDeckUseCase shuffleDeckUseCase;

    @Test
    @DisplayName("should deal 14 cards to each player")
    void shuffleDeck() {
        when(findGameByIdPort.findGameById(any())).thenReturn(createGameWithFourPlayers());
        var playerArgumentCaptor = ArgumentCaptor.forClass(Player.class);

        shuffleDeckUseCase.shuffleDeck(null);

        verify(savePlayerPort, times(4)).savePlayer(playerArgumentCaptor.capture());
        var players = playerArgumentCaptor.getAllValues();
        assertThat(players.size()).isEqualTo(4);
        assertThat(players.stream()
                .map(Player::cards)
                .map(List::size)
                .filter(size -> size == 14)
                .count()).isEqualTo(4);
    }

    private static Game createGameWithFourPlayers() {
        return new Game.Builder(null, GamePhase.DEALING_CARDS)
                .firstTeam(new Team.Builder(null)
                        .firstPlayer(new Player.Builder(null).build())
                        .secondPlayer(new Player.Builder(null).build())
                        .build())
                .secondTeam(new Team.Builder(null)
                        .firstPlayer(new Player.Builder(null).build())
                        .secondPlayer(new Player.Builder(null).build())
                        .build())
                .build();
    }
}