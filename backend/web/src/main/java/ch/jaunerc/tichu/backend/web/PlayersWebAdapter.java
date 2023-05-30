package ch.jaunerc.tichu.backend.web;

import ch.jaunerc.tichu.backend.domain.player.port.CreatePlayerUseCase;
import ch.jaunerc.tichu.backend.web.api.controller.PlayersApiDelegate;
import ch.jaunerc.tichu.backend.web.api.model.PlayerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public record PlayersWebAdapter(CreatePlayerUseCase createPlayerUseCase) implements PlayersApiDelegate {

    @Override
    public ResponseEntity<PlayerDto> createPlayer(String playerName) {
        var player = createPlayerUseCase.createPlayer(playerName);
        return ResponseEntity.ok(new PlayerDto(player.id().toString(), player.name()));
    }
}
