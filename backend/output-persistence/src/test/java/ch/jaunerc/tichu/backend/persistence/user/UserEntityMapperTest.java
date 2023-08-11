package ch.jaunerc.tichu.backend.persistence.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserEntityMapperTest {

    @Test
    @DisplayName("should create a user entity without setting the id")
    void map() {
        var result = UserEntityMapper.map("Douglas");

        assertThat(result.getName()).isEqualTo("Douglas");
        assertThat(result.getId()).isNull();
    }
}