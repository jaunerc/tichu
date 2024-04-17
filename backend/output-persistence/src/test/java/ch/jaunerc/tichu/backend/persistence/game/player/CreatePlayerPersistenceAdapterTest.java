package ch.jaunerc.tichu.backend.persistence.game.player;

import ch.jaunerc.tichu.backend.domain.user.model.User;
import ch.jaunerc.tichu.backend.persistence.user.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static ch.jaunerc.tichu.backend.domain.game.model.PlayerSeatId.FIRST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePlayerPersistenceAdapterTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private CreatePlayerPersistenceAdapter createPlayerPersistenceAdapter;

    @Test
    void createPlayer() {
        var userId = UUID.randomUUID();
        var user = new User(userId, "Adam");
        var playerEntity = createPlayerEntity(userId);
        when(playerRepository.save(any())).thenReturn(playerEntity);

        var result = createPlayerPersistenceAdapter.createPlayer(user, FIRST);

        assertThat(result.user().id()).isEqualTo(userId);
    }

    private static PlayerEntity createPlayerEntity(UUID userId) {
        var userEntity = new UserEntity();
        userEntity.setId(userId);
        var playerEntity = new PlayerEntity();
        playerEntity.setUser(userEntity);
        return playerEntity;
    }
}