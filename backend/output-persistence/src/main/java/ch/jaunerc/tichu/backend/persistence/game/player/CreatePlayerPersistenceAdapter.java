package ch.jaunerc.tichu.backend.persistence.game.player;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.PlayerSeatId;
import ch.jaunerc.tichu.backend.domain.game.port.output.CreatePlayerOutputPort;
import ch.jaunerc.tichu.backend.domain.user.model.User;
import ch.jaunerc.tichu.backend.persistence.user.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePlayerPersistenceAdapter implements CreatePlayerOutputPort {

    private final PlayerRepository playerRepository;

    @Override
    public Player createPlayer(User user, PlayerSeatId playerSeatId) {
        var playerEntity = new PlayerEntity();
        playerEntity.setUser(UserEntityMapper.map(user));
        playerEntity.setPlayerSeatId(playerSeatId);
        return PlayerEntityMapper.toDomain(playerRepository.save(playerEntity));
    }
}
