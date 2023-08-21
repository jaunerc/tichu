package ch.jaunerc.tichu.backend.web.users;

import ch.jaunerc.tichu.backend.domain.user.model.User;
import ch.jaunerc.tichu.backend.web.api.model.UserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDtoMapper {

    static UserDto map(User user) {
        return new UserDto(user.id().toString(), user.name());
    }
}
