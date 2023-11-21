package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.domain.game.port.FindGameByIdPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountReadyPlayersServiceTest {

    @Mock
    private FindGameByIdPort findGameByIdPort;
    @InjectMocks
    private CountReadyPlayersService countReadyPlayersService;

    @Test
    void countReadyPlayers() {
        when(findGameByIdPort.findGameById(any())).thenReturn(createGameWithThreePlayers());

        var result = countReadyPlayersService.countReadyPlayers(null);

        assertThat(result).isEqualTo(3);
    }

    @Test
    void countReadyPlayers_nullTeam() {
        when(findGameByIdPort.findGameById(any())).thenReturn(createGameWithNullTeam());

        var result = countReadyPlayersService.countReadyPlayers(null);

        assertThat(result).isEqualTo(2);
    }

    private Game createGameWithThreePlayers() {
        return new Game.Builder(null, GamePhase.DEALING_CARDS)
                .firstTeam(new Team.Builder(null)
                        .firstPlayer(new Player(null, null, List.of()))
                        .secondPlayer(new Player(null, null, List.of()))
                        .build())
                .secondTeam(new Team.Builder(null)
                        .secondPlayer(new Player(null, null, List.of()))
                        .build())
                .build();
    }

    private Game createGameWithNullTeam() {
        return new Game.Builder(null, GamePhase.DEALING_CARDS)
                .firstTeam(new Team.Builder(null)
                        .firstPlayer(new Player(null, null, List.of()))
                        .secondPlayer(new Player(null, null, List.of()))
                        .build())
                .build();
    }
}