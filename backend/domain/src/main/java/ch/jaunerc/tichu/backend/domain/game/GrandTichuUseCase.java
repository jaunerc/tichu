package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.port.input.GrandTichuInputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindGameByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindPlayerByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.SavePlayerOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GrandTichuUseCase implements GrandTichuInputPort {

    private final FindGameByIdOutputPort findGameByIdPort;
    private final FindPlayerByIdOutputPort findPlayerByIdOutputPort;
    private final SavePlayerOutputPort savePlayerPort;

    @Override
    public Game grandTichuByPlayer(UUID gameId, UUID playerId, boolean isGrandTichuCalled) {
        var player = findPlayerByIdOutputPort.findPlayerById(playerId);

        savePlayerPort.savePlayer(Player.Builder.of(player)
                .grandTichuCalled(isGrandTichuCalled)
                .build());

        return findGameByIdPort.findGameById(gameId);
    }
}
