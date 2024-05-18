package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.domain.game.port.output.CreatePlayerOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindGameByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindUserByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.SaveGameOutputPort;
import ch.jaunerc.tichu.backend.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.model.GamePhase.GAME_IS_RUNNING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JoinGameUseCaseTest {

    @Mock
    private FindGameByIdOutputPort findGameByIdPort;
    @Mock
    private FindUserByIdOutputPort findUserByIdOutputPort;
    @Mock
    private CreatePlayerOutputPort createPlayerOutputPort;
    @Mock
    private SaveGameOutputPort saveGamePort;

    @InjectMocks
    private JoinGameUseCase joinGameUseCase;

    @BeforeEach
    void setup() {
        when(findUserByIdOutputPort.findUserById(any())).thenReturn(new User(UUID.randomUUID(), "Adam"));
    }

    @Test
    @DisplayName("should throw an exception when the requested game has no free capacity")
    void joinGame_noFreeCapacity_exception() {
        var gameWithNoCapacity = new Game.Builder(null, GAME_IS_RUNNING)
                .firstTeam(new Team(null, createEmptyPlayer(), createEmptyPlayer(), 0))
                .secondTeam(new Team(null, createEmptyPlayer(), createEmptyPlayer(), 0))
                .build();
        when(findGameByIdPort.findGameById(any())).thenReturn(gameWithNoCapacity);

        assertThrows(IllegalArgumentException.class, () -> joinGameUseCase.joinGame(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
    }

    @Test
    @DisplayName("should create a new team when the team is null")
    void joinGame_teamIsNull_createTeam() {
        var gameWithNoCapacity = new Game.Builder(null, GAME_IS_RUNNING)
                .secondTeam(new Team(null, createEmptyPlayer(), createEmptyPlayer(), 0))
                .build();
        var playerId = UUID.randomUUID();
        var player = new Player.Builder(playerId).build();
        when(findGameByIdPort.findGameById(any())).thenReturn(gameWithNoCapacity);
        when(createPlayerOutputPort.createPlayer(any(), any())).thenReturn(player);
        when(saveGamePort.saveGame(any())).thenReturn(new Game.Builder(UUID.randomUUID(), null).build());

        var result = joinGameUseCase.joinGame(UUID.randomUUID().toString(), UUID.randomUUID().toString());

        assertThat(result.playerId()).isEqualTo(playerId);
    }

    @Test
    @DisplayName("should save the game when the teams have capacity")
    void joinGame_teamHasCapacity_saveGame() {
        var gameWithNoCapacity = new Game.Builder(null, GAME_IS_RUNNING)
                .firstTeam(new Team(null, null, createEmptyPlayer(), 0))
                .secondTeam(new Team(null, createEmptyPlayer(), createEmptyPlayer(), 0))
                .build();
        var playerId = UUID.randomUUID();
        var player = new Player.Builder(playerId).build();
        when(findGameByIdPort.findGameById(any())).thenReturn(gameWithNoCapacity);
        when(createPlayerOutputPort.createPlayer(any(), any())).thenReturn(player);
        when(saveGamePort.saveGame(any())).thenReturn(new Game.Builder(UUID.randomUUID(), null).build());

        var result = joinGameUseCase.joinGame(UUID.randomUUID().toString(), UUID.randomUUID().toString());

        verify(saveGamePort).saveGame(any());
        assertThat(result.playerId()).isEqualTo(playerId);
    }

    private static Player createEmptyPlayer() {
        return new Player.Builder(null).build();
    }
}