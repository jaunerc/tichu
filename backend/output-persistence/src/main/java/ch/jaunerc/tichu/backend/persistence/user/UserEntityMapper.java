package ch.jaunerc.tichu.backend.persistence.user;

import ch.jaunerc.tichu.backend.domain.user.model.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntityMapper {

    static UserEntity map(String userName) {
        var userEntity = new UserEntity();
        userEntity.setName(userName);
        return userEntity;
    }

    public static UserEntity map(User user) {
        return new UserEntity(user.id(), user.name());
    }

    public static User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getName()
        );
    }
}
