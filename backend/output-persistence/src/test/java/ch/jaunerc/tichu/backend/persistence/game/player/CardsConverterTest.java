package ch.jaunerc.tichu.backend.persistence.game.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static ch.jaunerc.tichu.backend.domain.game.model.card.Card.*;
import static org.assertj.core.api.Assertions.assertThat;

class CardsConverterTest {

    private CardsConverter cardsConverter;

    @BeforeEach
    void setup() {
        cardsConverter = new CardsConverter();
    }

    @Test
    void convertToDatabaseColumn_null() {
        assertThat(cardsConverter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void convertToDatabaseColumn() {
        var cards = List.of(DOG, DRAGON, PHOENIX);

        var result = cardsConverter.convertToDatabaseColumn(cards);

        assertThat(result).isEqualTo("DOG;DRAGON;PHOENIX");
    }

    @Test
    void convertToEntityAttribute_null() {
        assertThat(cardsConverter.convertToEntityAttribute(null)).isNull();
    }

    @Test
    void convertToEntityAttribute() {
        var cards = "DOG;PHOENIX;TWO_JADE";

        var result = cardsConverter.convertToEntityAttribute(cards);

        assertThat(result).containsExactly(DOG, PHOENIX, TWO_JADE);
    }
}