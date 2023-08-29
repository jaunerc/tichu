package ch.jaunerc.tichu.backend.persistence.game.player;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.port.CreatePlayerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePlayerPersistenceAdapter implements CreatePlayerPort {

    private final PlayerRepository playerRepository;

    @Override
    public Player createPlayer() {
        return PlayerEntityMapper.toDomain(playerRepository.save(new PlayerEntity()));
    }
}
