package ch.jaunerc.tichu.backend.web.users;

import ch.jaunerc.tichu.backend.domain.user.model.User;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserDtoMapperTest {

    @Test
    void map() {
        var uuid = "00000000-0000-0000-0000-000000000000";
        var userName = "Champion";
        var user = new User(UUID.fromString(uuid), userName);

        var result = UserDtoMapper.map(user);

        assertThat(result.getId()).isEqualTo(uuid);
        assertThat(result.getName()).isEqualTo(userName);
    }
}