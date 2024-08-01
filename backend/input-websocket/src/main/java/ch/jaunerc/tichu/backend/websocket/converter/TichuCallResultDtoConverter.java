package ch.jaunerc.tichu.backend.websocket.converter;

import ch.jaunerc.tichu.backend.domain.game.model.TichuCallResult;
import ch.jaunerc.tichu.backend.websocket.api.model.TichuCallResultDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class TichuCallResultDtoConverter {

    public static TichuCallResult toDomain(TichuCallResultDto dto) {
        return switch (dto) {
            case GRAND_CALLED -> TichuCallResult.GRAND_CALLED;
            case GRAND_NOT_CALLED -> TichuCallResult.GRAND_NOT_CALLED;
            case SMALL_CALLED -> TichuCallResult.SMALL_CALLED;
            case NONE_CALLED -> TichuCallResult.NONE_CALLED;
        };
    }
}
