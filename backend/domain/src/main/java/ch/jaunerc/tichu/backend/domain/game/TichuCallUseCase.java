package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.*;
import ch.jaunerc.tichu.backend.domain.game.port.input.DealCardsInputPort;
import ch.jaunerc.tichu.backend.domain.game.port.input.TichuCallInputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindGameByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindPlayerByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.SavePlayerOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.SendPlayerPrivateStateOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TichuCallUseCase implements TichuCallInputPort {

    private final FindGameByIdOutputPort findGameByIdPort;
    private final FindPlayerByIdOutputPort findPlayerByIdOutputPort;
    private final SavePlayerOutputPort savePlayerPort;
    private final DealCardsInputPort dealCardsInputPort;
    private final SendPlayerPrivateStateOutputPort sendPlayerPrivateStateOutputPort;

    @Override
    public Game smallTichuByPlayer(UUID gameId, UUID playerId, boolean isSmallTichuCalled) {
        var player = findPlayerByIdOutputPort.findPlayerById(playerId);

        var updatedPlayer = Player.Builder.of(player)
                .smallTichuCalled(mapTichuCalled(isSmallTichuCalled))
                .build();

        savePlayerPort.savePlayer(updatedPlayer);

        return findGameByIdPort.findGameById(gameId);
    }

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

    @Override
    public Game tichuCallByPlayer(UUID gameId, UUID playerId, TichuCallResult tichuCallResult) {
        var player = findPlayerByIdOutputPort.findPlayerById(playerId);

        var playerBuilder = Player.Builder.of(player);

        return switch (tichuCallResult) {
            case GRAND_CALLED -> onGrandTichuCall(gameId, playerBuilder);
            case GRAND_NOT_CALLED -> onGrandTichuNotCalled(gameId, playerBuilder);
            case SMALL_CALLED -> updatePlayer(playerBuilder.tichuCall(TichuCall.SMALL), gameId);
            case NONE_CALLED -> updatePlayer(playerBuilder.tichuCall(TichuCall.NONE), gameId);
        };
    }

    private Game onGrandTichuNotCalled(UUID gameId, Player.Builder playerBuilder) {
        sendPlayerPrivateStateForGrandTichu(gameId, playerBuilder);
        return findGameByIdPort.findGameById(gameId);
    }

    private Game onGrandTichuCall(UUID gameId, Player.Builder playerBuilder) {
        var updatedPlayer = sendPlayerPrivateStateForGrandTichu(gameId, playerBuilder.tichuCall(TichuCall.GRAND));
        savePlayerPort.savePlayer(updatedPlayer);
        return findGameByIdPort.findGameById(gameId);
    }

    private Player sendPlayerPrivateStateForGrandTichu(UUID gameId, Player.Builder playerBuilder) {
        var updatedPlayer = playerBuilder.build();
        sendPlayerPrivateStateOutputPort.sendPlayerPrivateState(
                gameId,
                Player.Builder.of(updatedPlayer)
                        .build());
        return updatedPlayer;
    }

    private Game updatePlayer(Player.Builder playerBuilder, UUID gameId) {
        savePlayerPort.savePlayer(playerBuilder.build());
        return findGameByIdPort.findGameById(gameId);
    }

    private static TichuCalled mapTichuCalled(boolean tichuCalled) {
        return tichuCalled ? TichuCalled.CALLED : TichuCalled.NOT_CALLED;
    }
}
