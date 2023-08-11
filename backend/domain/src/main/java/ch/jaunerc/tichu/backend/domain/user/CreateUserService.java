package ch.jaunerc.tichu.backend.domain.user;

import ch.jaunerc.tichu.backend.domain.user.model.User;
import ch.jaunerc.tichu.backend.domain.user.port.CreateUserPort;
import ch.jaunerc.tichu.backend.domain.user.port.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {

    private final CreateUserPort createUserPort;
    @Override
    public User createUser(String userName) {
        return createUserPort.createUser(userName);
    }
}
