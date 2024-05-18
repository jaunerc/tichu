package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindGameByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.SavePlayerOutputPort;
import ch.jaunerc.tichu.backend.domain.game.usecase.PushCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PushCardService implements PushCardUseCase {

    private final FindGameByIdOutputPort findGameByIdPort;
    private final SavePlayerOutputPort savePlayerPort;

    @Override
    public Optional<List<Card>> pushCard(UUID gameId, Card card, UUID fromPlayerId, int toPlayerNumber) {
        var game = findGameByIdPort.findGameById(gameId);
        var players = PlayerCollector.listOfPlayersOrderedBySeatPosition(game);

        var fromPlayer = players.stream()
                .filter(player -> player.uuid().equals(fromPlayerId))
                .findFirst()
                .orElseThrow();
        var toPlayer = players.get(toPlayerNumber -1);

        var pushedCards = Stream.concat(fromPlayer.pushedCards().stream(), Stream.of(card)).toList();

        savePlayerPort.savePlayer(Player.Builder.of(fromPlayer)
                .pushedCards(pushedCards)
                .build());

        var receivedCards = Stream.concat(toPlayer.receivedCards().stream(), Stream.of(card)).toList();

        savePlayerPort.savePlayer(Player.Builder.of(toPlayer)
                .receivedCards(receivedCards)
                .build());

        if (pushedCards.size() == 3) {
            return Optional.of(fromPlayer.receivedCards());
        }

        return Optional.empty();
    }
}
