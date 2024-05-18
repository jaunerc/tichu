package ch.jaunerc.tichu.backend.domain.game.port.output;

import ch.jaunerc.tichu.backend.domain.user.model.User;

import java.util.UUID;

public interface FindUserByIdOutputPort {

    User findUserById(UUID userId);
}
