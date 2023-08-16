package ch.jaunerc.tichu.backend.persistence.game.player;

import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;

@Converter
public class CardsConverter implements AttributeConverter<List<Card>, String> {

    private static final String DELIMITER = ";";

    @Override
    public String convertToDatabaseColumn(List<Card> cards) {
        if (cards == null) {
            return null;
        }
        return String.join(DELIMITER, cards.stream()
                .map(Card::name)
                .toList());
    }

    @Override
    public List<Card> convertToEntityAttribute(String cardNames) {
        if (cardNames == null) {
            return null;
        }
        return Arrays.stream(cardNames.split(DELIMITER))
                .map(Card::valueOf)
                .toList();
    }
}
