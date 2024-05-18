package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindGameByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.SavePlayerOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GrandTichuUseCaseTest {

    @Mock
    private FindGameByIdOutputPort findGameByIdOutputPort;
    @Mock
    private SavePlayerOutputPort savePlayerPort;
    @InjectMocks
    private GrandTichuUseCase grandTichuUseCase;

    @Test
    void grandTichuByPlayer() {
        var uuidPlayer1 = UUID.randomUUID();
        var uuidPlayer2 = UUID.randomUUID();
        var uuidPlayer3 = UUID.randomUUID();
        var uuidPlayer4 = UUID.randomUUID();

        when(findGameByIdOutputPort.findGameById(any())).thenReturn(
                createGameWithFourPlayers(uuidPlayer1, uuidPlayer2, uuidPlayer3, uuidPlayer4));

        var result = grandTichuUseCase.grandTichuByPlayer(null, uuidPlayer1, true);

        verify(savePlayerPort).savePlayer(any());
    }

    @Test
    void grandTichuByPlayer_unknownPlayer_exception() {
        var uuidPlayer1 = UUID.randomUUID();
        var uuidPlayer2 = UUID.randomUUID();
        var uuidPlayer3 = UUID.randomUUID();
        var uuidPlayer4 = UUID.randomUUID();

        when(findGameByIdOutputPort.findGameById(any())).thenReturn(
                createGameWithFourPlayers(uuidPlayer1, uuidPlayer2, uuidPlayer3, uuidPlayer4));

        assertThatThrownBy(() -> grandTichuUseCase.grandTichuByPlayer(null, UUID.randomUUID(), true))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Game createGameWithFourPlayers(
            UUID uuidPlayer1,
            UUID uuidPlayer2,
            UUID uuidPlayer3,
            UUID uuitPlayer4
    ) {
        return new Game.Builder(null, GamePhase.DEALING_CARDS)
                .firstTeam(new Team.Builder(null)
                        .firstPlayer(new Player.Builder(uuidPlayer1).build())
                        .secondPlayer(new Player.Builder(uuidPlayer2).build())
                        .build())
                .secondTeam(new Team.Builder(null)
                        .firstPlayer(new Player.Builder(uuidPlayer3).build())
                        .secondPlayer(new Player.Builder(uuitPlayer4).build())
                        .build())
                .build();
    }
}