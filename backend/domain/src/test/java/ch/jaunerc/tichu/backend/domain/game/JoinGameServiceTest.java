package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.domain.game.port.CreatePlayerPort;
import ch.jaunerc.tichu.backend.domain.game.port.FindGameByIdPort;
import ch.jaunerc.tichu.backend.domain.game.port.FindUserByIdPort;
import ch.jaunerc.tichu.backend.domain.game.port.SaveGamePort;
import ch.jaunerc.tichu.backend.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.model.GamePhase.GAME_IS_RUNNING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JoinGameServiceTest {

    @Mock
    private FindGameByIdPort findGameByIdPort;
    @Mock
    private FindUserByIdPort findUserByIdPort;
    @Mock
    private CreatePlayerPort createPlayerPort;
    @Mock
    private SaveGamePort saveGamePort;

    @InjectMocks
    private JoinGameService joinGameService;

    @BeforeEach
    void setup() {
        when(findUserByIdPort.findUserById(any())).thenReturn(new User(UUID.randomUUID(), "Adam"));
    }

    @Test
    @DisplayName("should throw an exception when the requested game has no free capacity")
    void joinGame_noFreeCapacity_exception() {
        var gameWithNoCapacity = new Game(
                null,
                new Team(null, createEmptyPlayer(), createEmptyPlayer(), 0),
                new Team(null, createEmptyPlayer(), createEmptyPlayer(), 0),
                GAME_IS_RUNNING);
        when(findGameByIdPort.findGameById(any())).thenReturn(gameWithNoCapacity);

        assertThrows(IllegalStateException.class, () -> joinGameService.joinGame(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
    }

    @Test
    @DisplayName("should create a new team when the team is null")
    void joinGame_teamIsNull_createTeam() {
        var gameWithNoCapacity = new Game(
                null,
                null,
                new Team(null, createEmptyPlayer(), createEmptyPlayer(), 0),
                GAME_IS_RUNNING);
        var playerId = UUID.randomUUID();
        var player = new Player(playerId, null, List.of());
        when(findGameByIdPort.findGameById(any())).thenReturn(gameWithNoCapacity);
        when(createPlayerPort.createPlayer(any())).thenReturn(player);
        when(saveGamePort.saveGame(any())).thenReturn(new Game(UUID.randomUUID(), null, null,  GAME_IS_RUNNING));

        var result = joinGameService.joinGame(UUID.randomUUID().toString(), UUID.randomUUID().toString());

        assertThat(result.playerId()).isEqualTo(playerId);
    }

    @Test
    @DisplayName("should save the game when the teams have capacity")
    void joinGame_teamHasCapacity_saveGame() {
        var gameWithNoCapacity = new Game(
                null,
                new Team(null, null, createEmptyPlayer(), 0),
                new Team(null, createEmptyPlayer(), createEmptyPlayer(), 0),
                GAME_IS_RUNNING);
        var playerId = UUID.randomUUID();
        var player = new Player(playerId, null, List.of());
        when(findGameByIdPort.findGameById(any())).thenReturn(gameWithNoCapacity);
        when(createPlayerPort.createPlayer(any())).thenReturn(player);
        when(saveGamePort.saveGame(any())).thenReturn(new Game(UUID.randomUUID(), null, null,  GAME_IS_RUNNING));

        var result = joinGameService.joinGame(UUID.randomUUID().toString(), UUID.randomUUID().toString());

        verify(saveGamePort).saveGame(any());
        assertThat(result.playerId()).isEqualTo(playerId);
    }

    private static Player createEmptyPlayer() {
        return new Player(null, null, List.of());
    }
}