package ch.jaunerc.tichu.backend.persistence.game.player;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.port.SavePlayerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavePlayerPersistenceAdapter implements SavePlayerPort {

    private final PlayerRepository playerRepository;

    @Override
    public void savePlayer(Player player) {
        var playerEntity = PlayerEntityMapper.map(player);
        playerRepository.save(playerEntity);
    }
}
