package ch.jaunerc.tichu.backend.persistence.game.player;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindPlayerByIdOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindPlayerByIdPersistenceAdapter implements FindPlayerByIdOutputPort {

    private final PlayerRepository playerRepository;

    @Override
    public Player findPlayerById(UUID playerId) {
        return playerRepository.findById(playerId)
                .map(PlayerEntityMapper::toDomain)
                .orElseThrow();
    }
}
