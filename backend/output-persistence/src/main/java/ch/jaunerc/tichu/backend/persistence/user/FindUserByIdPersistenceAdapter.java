package ch.jaunerc.tichu.backend.persistence.user;

import ch.jaunerc.tichu.backend.domain.game.port.output.FindUserByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindUserByIdPersistenceAdapter implements FindUserByIdOutputPort {

    private final UserRepository userRepository;

    @Override
    public User findUserById(UUID userId) {
        return UserEntityMapper.toDomain(userRepository.getReferenceById(userId));
    }
}
