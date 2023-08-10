package ch.jaunerc.tichu.backend.persistence.game;

import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class GamePhaseConverter implements AttributeConverter<GamePhase, String> {
    @Override
    public String convertToDatabaseColumn(GamePhase gamePhase) {
        if (gamePhase == null) {
            return null;
        }
        return gamePhase.name();
    }

    @Override
    public GamePhase convertToEntityAttribute(String name) {
        if (name == null) {
            return null;
        }
        return Stream.of(GamePhase.values())
                .filter(gamePhase -> gamePhase.name().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
