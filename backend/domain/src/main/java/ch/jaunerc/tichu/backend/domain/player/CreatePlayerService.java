package ch.jaunerc.tichu.backend.domain.player;

import ch.jaunerc.tichu.backend.domain.player.model.Player;
import ch.jaunerc.tichu.backend.domain.player.port.CreatePlayerUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreatePlayerService implements CreatePlayerUseCase {
    @Override
    public Player createPlayer(String playerName) {
        return new Player(UUID.randomUUID(), playerName);
    }
}
