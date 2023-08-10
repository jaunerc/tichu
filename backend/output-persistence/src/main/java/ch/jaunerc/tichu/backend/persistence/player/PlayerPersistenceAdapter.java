package ch.jaunerc.tichu.backend.persistence.player;

import ch.jaunerc.tichu.backend.domain.player.model.Player;
import ch.jaunerc.tichu.backend.domain.player.port.CreatePlayerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerPersistenceAdapter implements CreatePlayerPort {

    private final PlayerRepository playerRepository;

    @Override
    public Player createPlayer(String playerName) {
        var savedEntity = playerRepository.save(
                PlayerEntityMapper.map(playerName));
        return new Player(savedEntity.getId(), savedEntity.getName());
    }
}
