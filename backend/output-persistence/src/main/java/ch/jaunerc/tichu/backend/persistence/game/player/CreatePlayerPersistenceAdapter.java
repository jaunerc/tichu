package ch.jaunerc.tichu.backend.persistence.game.player;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.port.CreatePlayerPort;
import ch.jaunerc.tichu.backend.domain.user.model.User;
import ch.jaunerc.tichu.backend.persistence.user.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePlayerPersistenceAdapter implements CreatePlayerPort {

    private final PlayerRepository playerRepository;

    @Override
    public Player createPlayer(User user) {
        var playerEntity = new PlayerEntity();
        playerEntity.setUser(UserEntityMapper.map(user));
        return PlayerEntityMapper.toDomain(playerRepository.save(playerEntity));
    }
}
