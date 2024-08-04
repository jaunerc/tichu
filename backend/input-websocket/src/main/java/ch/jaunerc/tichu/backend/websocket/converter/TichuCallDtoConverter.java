package ch.jaunerc.tichu.backend.websocket.converter;

import ch.jaunerc.tichu.backend.domain.game.model.TichuCall;
import ch.jaunerc.tichu.backend.websocket.api.model.TichuCallDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TichuCallDtoConverter {

    public static TichuCallDto convert(TichuCall tichuCall) {
        if (tichuCall == null) {
            return null;
        }
        return switch (tichuCall) {
            case GRAND -> TichuCallDto.GRAND;
            case SMALL -> TichuCallDto.SMALL;
            case NONE -> TichuCallDto.NONE;
        };
    }
}
