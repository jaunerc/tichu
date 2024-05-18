package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindGameByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.SaveGameOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static ch.jaunerc.tichu.backend.domain.game.model.GamePhase.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangeGamePhaseServiceTest {

    @Mock
    private FindGameByIdOutputPort findGameByIdPort;
    @Mock
    private SaveGameOutputPort saveGamePort;
    @InjectMocks
    private ChangeGamePhaseService changeGamePhaseService;

    @ParameterizedTest
    @MethodSource("provideGamePhases")
    void changeGamePhase_validChanges(GamePhase from, GamePhase to) {
        ArgumentCaptor<Game> gameCaptor = ArgumentCaptor.forClass(Game.class);
        when(findGameByIdPort.findGameById(any())).thenReturn(
                new Game.Builder(null, from).build()
        );

        changeGamePhaseService.changeGamePhase(null);

        verify(saveGamePort).saveGame(gameCaptor.capture());
        assertThat(gameCaptor.getValue().gamePhase()).isEqualTo(to);
    }

    @Test
    void changeGamePhase_gameHasEnded_exception() {
        when(findGameByIdPort.findGameById(any())).thenReturn(
                new Game.Builder(null, GAME_ENDED).build()
        );

        assertThatThrownBy(() -> changeGamePhaseService.changeGamePhase(null))
                .isInstanceOf(IllegalStateException.class);
    }

    private static Stream<Arguments> provideGamePhases() {
        return Stream.of(
                Arguments.of(PLAYERS_JOINING, DEALING_CARDS),
                Arguments.of(DEALING_CARDS, FIRST_EIGHT_CARDS_ARE_DEALT),
                Arguments.of(FIRST_EIGHT_CARDS_ARE_DEALT, ALL_CARDS_ARE_DEALT),
                Arguments.of(GAME_IS_RUNNING, GAME_ENDED)
        );
    }
}