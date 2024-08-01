package ch.jaunerc.tichu.backend.websocket.converter;

import ch.jaunerc.tichu.backend.domain.game.model.TichuCall;
import ch.jaunerc.tichu.backend.websocket.api.model.TichuCallDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class TichuCallDtoConverter {

    public static TichuCall toDomain(TichuCallDto dto) {
        return switch (dto) {
            case NOT_ANSWERED -> TichuCall.NOT_ANSWERED;
            case GRAND -> TichuCall.GRAND;
            case SMALL -> TichuCall.SMALL;
            case NONE -> TichuCall.NONE;
        };
    }

    public static TichuCallDto toDto(TichuCall tichuCall) {
        return switch (tichuCall) {
            case GRAND -> TichuCallDto.GRAND;
            case SMALL -> TichuCallDto.SMALL;
            case NONE -> TichuCallDto.NONE;
            case NOT_ANSWERED -> TichuCallDto.NOT_ANSWERED;
        };
    }
}
