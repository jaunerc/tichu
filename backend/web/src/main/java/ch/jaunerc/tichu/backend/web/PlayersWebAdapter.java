package ch.jaunerc.tichu.backend.web;

import ch.jaunerc.tichu.backend.web.api.controller.PlayersApiDelegate;
import ch.jaunerc.tichu.backend.web.api.model.PlayerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlayersWebAdapter implements PlayersApiDelegate {

    @Override
    public ResponseEntity<PlayerDto> getPlayer() {
        return ResponseEntity.ok(new PlayerDto());
    }
}
