package ch.jaunerc.tichu.backend.domain.user.port;

import ch.jaunerc.tichu.backend.domain.user.model.User;

public interface CreateUserUseCase {

    User createUser(String userName);
}
