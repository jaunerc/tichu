package ch.jaunerc.tichu.backend.persistence.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindGameByIdPersistenceAdapterTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private FindGameByIdPersistenceAdapter findGameByIdPersistenceAdapter;

    @Test
    void findGameById_unknownGameId_exception() {
        assertThrows(NoSuchElementException.class, () -> findGameByIdPersistenceAdapter.findGameById(UUID.randomUUID()));
    }

    @Test
    void findGameById() {
        when(gameRepository.findById(any())).thenReturn(Optional.of(new GameEntity()));

        var result = findGameByIdPersistenceAdapter.findGameById(UUID.randomUUID());

        assertThat(result).isNotNull();
    }
}