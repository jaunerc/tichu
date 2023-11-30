package ch.jaunerc.tichu.backend.persistence.game.team;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateTeamPersistenceAdapterTest {

    @Mock
    private TeamRepository teamRepository;

    @Captor
    private ArgumentCaptor<TeamEntity> teamEntityArgumentCaptor;

    @InjectMocks
    private CreateTeamPersistenceAdapter createTeamPersistenceAdapter;

    @Test
    @DisplayName("should set the given player as first player")
    void createTeam() {
        when(teamRepository.save(any())).thenReturn(new TeamEntity());
        var user = new User(UUID.randomUUID(), "Eve");
        var player = new Player.Builder(UUID.randomUUID())
                .user(user)
                .build();

        createTeamPersistenceAdapter.createTeam(player);

        verify(teamRepository).save(teamEntityArgumentCaptor.capture());
        var capturedTeamEntity = teamEntityArgumentCaptor.getValue();
        assertThat(capturedTeamEntity.getFirstPlayer()).isNotNull();
        assertThat(capturedTeamEntity.getId()).isNull();
        assertThat(capturedTeamEntity.getSecondPlayer()).isNull();
    }
}