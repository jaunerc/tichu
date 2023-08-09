package ch.jaunerc.tichu.backend.web.players;

import ch.jaunerc.tichu.backend.domain.player.port.CreatePlayerUseCase;
import ch.jaunerc.tichu.backend.web.api.controller.PlayersApiDelegate;
import ch.jaunerc.tichu.backend.web.api.model.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayersWebAdapter implements PlayersApiDelegate {

    private final CreatePlayerUseCase createPlayerUseCase;

    @Override
    public ResponseEntity<PlayerDto> createPlayer(String playerName) {
        return ResponseEntity.ok(PlayerDtoMapper.map(
                createPlayerUseCase.createPlayer(playerName)));
    }
}
