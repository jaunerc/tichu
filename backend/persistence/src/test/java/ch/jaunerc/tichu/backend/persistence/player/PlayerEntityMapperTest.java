package ch.jaunerc.tichu.backend.persistence.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerEntityMapperTest {

    @Test
    @DisplayName("should create a player entity without setting the id")
    void map() {
        var result = PlayerEntityMapper.map("Douglas");

        assertThat(result.getName()).isEqualTo("Douglas");
        assertThat(result.getId()).isNull();
    }
}