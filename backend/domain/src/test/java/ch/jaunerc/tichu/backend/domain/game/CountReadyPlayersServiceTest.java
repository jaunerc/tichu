package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindGameByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.usecase.ChangeGamePhaseUseCase;
import ch.jaunerc.tichu.backend.domain.game.usecase.ShuffleDeckUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountReadyPlayersServiceTest {

    @Mock
    private FindGameByIdOutputPort findGameByIdOutputPort;
    @Mock
    private ChangeGamePhaseUseCase changeGamePhaseUseCase;
    @Mock
    private ShuffleDeckUseCase shuffleDeckUseCase;
    @InjectMocks
    private ReadyPlayerService countReadyPlayersService;

    @Test
    void countReadyPlayers() {
        when(findGameByIdOutputPort.findGameById(any())).thenReturn(createGameWithThreePlayers());

        var result = countReadyPlayersService.updateReadyPlayers(null);

        assertThat(result).isEqualTo(3);
    }

    @Test
    void countReadyPlayers_allPlayersReady_updateGameStateAndShuffleDeck() {
        when(findGameByIdOutputPort.findGameById(any())).thenReturn(createGameWithFourPlayers());

        var result = countReadyPlayersService.updateReadyPlayers(null);

        verify(changeGamePhaseUseCase).changeGamePhase(any());
        verify(shuffleDeckUseCase).shuffleDeck(any());
        assertThat(result).isEqualTo(4);
    }

    @Test
    void countReadyPlayers_nullTeam() {
        when(findGameByIdOutputPort.findGameById(any())).thenReturn(createGameWithNullTeam());

        var result = countReadyPlayersService.updateReadyPlayers(null);

        assertThat(result).isEqualTo(2);
    }

    private Game createGameWithThreePlayers() {
        return new Game.Builder(null, GamePhase.DEALING_CARDS)
                .firstTeam(new Team.Builder(null)
                        .firstPlayer(mockPlayer())
                        .secondPlayer(mockPlayer())
                        .build())
                .secondTeam(new Team.Builder(null)
                        .secondPlayer(mockPlayer())
                        .build())
                .build();
    }

    private Game createGameWithFourPlayers() {
        return new Game.Builder(null, GamePhase.DEALING_CARDS)
                .firstTeam(new Team.Builder(null)
                        .firstPlayer(mockPlayer())
                        .secondPlayer(mockPlayer())
                        .build())
                .secondTeam(new Team.Builder(null)
                        .firstPlayer(mockPlayer())
                        .secondPlayer(mockPlayer())
                        .build())
                .build();
    }

    private Game createGameWithNullTeam() {
        return new Game.Builder(null, GamePhase.DEALING_CARDS)
                .firstTeam(new Team.Builder(null)
                        .firstPlayer(mockPlayer())
                        .secondPlayer(mockPlayer())
                        .build())
                .build();
    }

    private static Player mockPlayer() {
        return new Player.Builder(null).build();
    }
}