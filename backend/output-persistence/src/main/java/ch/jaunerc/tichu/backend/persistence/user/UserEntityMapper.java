package ch.jaunerc.tichu.backend.persistence.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntityMapper {

    static UserEntity map (String userName) {
        var userEntity = new UserEntity();
        userEntity.setName(userName);
        return userEntity;
    }
}
