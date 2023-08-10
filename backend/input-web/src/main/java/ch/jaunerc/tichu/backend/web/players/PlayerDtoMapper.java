package ch.jaunerc.tichu.backend.web.players;

import ch.jaunerc.tichu.backend.domain.player.model.Player;
import ch.jaunerc.tichu.backend.web.api.model.PlayerDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerDtoMapper {

    static PlayerDto map(Player player) {
        return new PlayerDto(player.id().toString(), player.name());
    }
}
