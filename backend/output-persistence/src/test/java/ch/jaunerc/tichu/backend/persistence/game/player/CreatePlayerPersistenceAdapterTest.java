package ch.jaunerc.tichu.backend.persistence.game.player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        when(playerRepository.save(any())).thenReturn(new PlayerEntity());

        createPlayerPersistenceAdapter.createPlayer();
    }
}