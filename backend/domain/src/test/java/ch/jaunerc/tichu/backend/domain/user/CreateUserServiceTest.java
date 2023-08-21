package ch.jaunerc.tichu.backend.domain.user;

import ch.jaunerc.tichu.backend.domain.user.model.User;
import ch.jaunerc.tichu.backend.domain.user.port.CreateUserPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

    @Mock
    private CreateUserPort createUserPort;
    @InjectMocks
    private CreateUserService createPlayerService;

    @Test
    void createPlayer() {
        var playerName = "The chosen one";
        when(createUserPort.createUser(anyString())).thenReturn(new User(UUID.randomUUID(), playerName));

        var player = createPlayerService.createUser(playerName);

        assertThat(player.id()).isNotNull();
        assertThat(player.name()).isEqualTo(playerName);
    }
}