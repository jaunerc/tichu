package ch.jaunerc.tichu.backend.domain.game.port;

import ch.jaunerc.tichu.backend.domain.user.model.User;

import java.util.UUID;

public interface FindUserByIdPort {

    User findUserById(UUID userId);
}
