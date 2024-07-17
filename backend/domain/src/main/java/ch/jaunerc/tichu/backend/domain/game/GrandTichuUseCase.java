package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.TichuCalled;
import ch.jaunerc.tichu.backend.domain.game.port.input.DealCardsInputPort;
import ch.jaunerc.tichu.backend.domain.game.port.input.GrandTichuInputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindGameByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindPlayerByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.SavePlayerOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.SendPlayerPrivateStateOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GrandTichuUseCase implements GrandTichuInputPort {

    private final FindGameByIdOutputPort findGameByIdPort;
    private final FindPlayerByIdOutputPort findPlayerByIdOutputPort;
    private final SavePlayerOutputPort savePlayerPort;
    private final DealCardsInputPort dealCardsInputPort;
    private final SendPlayerPrivateStateOutputPort sendPlayerPrivateStateOutputPort;

    @Override
    public Game grandTichuByPlayer(UUID gameId, UUID playerId, boolean isGrandTichuCalled) {
        var player = findPlayerByIdOutputPort.findPlayerById(playerId);

        var updatedPlayer = Player.Builder.of(player)
                .grandTichuCalled(mapTichuCalled(isGrandTichuCalled))
                .build();

        sendPlayerPrivateStateOutputPort.sendPlayerPrivateState(
                gameId,
                Player.Builder.of(updatedPlayer)
                        .cards(dealCardsInputPort.dealCards(gameId, playerId))
                        .build());

        savePlayerPort.savePlayer(updatedPlayer);

        return findGameByIdPort.findGameById(gameId);
    }

    private static TichuCalled mapTichuCalled(boolean isGrandTichuCalled) {
        return isGrandTichuCalled ? TichuCalled.CALLED : TichuCalled.NOT_CALLED;
    }
}
