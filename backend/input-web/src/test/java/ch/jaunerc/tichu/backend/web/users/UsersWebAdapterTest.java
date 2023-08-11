package ch.jaunerc.tichu.backend.web.users;

import ch.jaunerc.tichu.backend.domain.user.model.User;
import ch.jaunerc.tichu.backend.domain.user.port.CreateUserUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersWebAdapterTest {

    @Mock
    private CreateUserUseCase createUserUseCase;

    @InjectMocks
    private UsersWebAdapter usersWebAdapter;

    @Test
    void createPlayer() {
        var uuid = "00000000-0000-0000-0000-000000000000";
        var userName = "The captain";
        when(createUserUseCase.createUser(anyString()))
                .thenReturn(new User(UUID.fromString(uuid), userName));

        // act
        var responseEntity = usersWebAdapter.createUser(userName);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getId()).isEqualTo(uuid);
        assertThat(responseEntity.getBody().getName()).isEqualTo(userName);
    }
}