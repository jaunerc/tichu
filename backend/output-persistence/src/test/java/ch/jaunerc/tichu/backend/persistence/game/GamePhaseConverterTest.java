package ch.jaunerc.tichu.backend.persistence.game;

import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static ch.jaunerc.tichu.backend.domain.game.model.GamePhase.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GamePhaseConverterTest {

    private GamePhaseConverter gamePhaseConverter;

    @BeforeEach
    void setup() {
        gamePhaseConverter = new GamePhaseConverter();
    }

    @Test
    void convertToDatabaseColumn_null() {
        assertThat(gamePhaseConverter.convertToDatabaseColumn(null)).isNull();
    }

    @ParameterizedTest
    @EnumSource(GamePhase.class)
    void convertToDatabaseColumn(GamePhase gamePhase) {
        assertThat(gamePhaseConverter.convertToDatabaseColumn(gamePhase)).isEqualTo(gamePhase.name());
    }

    @Test
    void convertToEntityAttribute_null() {
        assertThat(gamePhaseConverter.convertToEntityAttribute(null)).isNull();
    }

    @Test
    void convertToEntityAttribute_unknownValue_exception() {
        assertThrows(IllegalArgumentException.class, () -> gamePhaseConverter.convertToEntityAttribute("unknown"));
    }

    @ParameterizedTest
    @MethodSource("provideGamePhaseArguments")
    void convertToEntityAttribute(GamePhase gamePhase, String databaseValue) {
        assertThat(gamePhaseConverter.convertToEntityAttribute(databaseValue)).isEqualTo(gamePhase);
    }

    private static Stream<Arguments> provideGamePhaseArguments() {
        return Stream.of(
                Arguments.of(DEALING_CARDS, DEALING_CARDS.name()),
                Arguments.of(FIRST_EIGHT_CARDS_ARE_DEALT, FIRST_EIGHT_CARDS_ARE_DEALT.name()),
                Arguments.of(ALL_CARDS_ARE_DEALT, ALL_CARDS_ARE_DEALT.name()),
                Arguments.of(GAME_IS_RUNNING, GAME_IS_RUNNING.name()),
                Arguments.of(GAME_ENDED, GAME_ENDED.name())
        );
    }
}
