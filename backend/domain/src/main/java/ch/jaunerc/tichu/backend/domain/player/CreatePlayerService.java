package ch.jaunerc.tichu.backend.domain.player;

import ch.jaunerc.tichu.backend.domain.player.model.Player;
import ch.jaunerc.tichu.backend.domain.player.port.CreatePlayerPort;
import ch.jaunerc.tichu.backend.domain.player.port.CreatePlayerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePlayerService implements CreatePlayerUseCase {

    private final CreatePlayerPort createPlayerPort;
    @Override
    public Player createPlayer(String playerName) {
        return createPlayerPort.createPlayer(playerName);
    }
}
