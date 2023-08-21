package ch.jaunerc.tichu.backend.persistence.user;

import ch.jaunerc.tichu.backend.domain.user.model.User;
import ch.jaunerc.tichu.backend.domain.user.port.CreateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPersistenceAdapter implements CreateUserPort {

    private final UserRepository userRepository;

    @Override
    public User createUser(String userName) {
        var savedEntity = userRepository.save(
                UserEntityMapper.map(userName));
        return new User(savedEntity.getId(), savedEntity.getName());
    }
}
