package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.domain.game.port.output.FindGameByIdOutputPort;
import ch.jaunerc.tichu.backend.domain.game.port.output.SavePlayerOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DealCardsServiceTest {

    @Mock
    private FindGameByIdOutputPort findGameByIdPort;
    @Mock
    private SavePlayerOutputPort savePlayerPort;
    @InjectMocks
    private DealCardsService dealCardsService;

    @Test
    void dealCards_firstDealingRound_onlyEightCards() {
        var playerId = UUID.randomUUID();
        when(findGameByIdPort.findGameById(any())).thenReturn(
                mockGame(playerId, GamePhase.DEALING_CARDS, false));

        var result = dealCardsService.dealCards(null, playerId);

        assertThat(result.size()).isEqualTo(8);
        verify(savePlayerPort).savePlayer(any());
    }

    @Test
    void dealCards_secondDealingRound_allCards() {
        var playerId = UUID.randomUUID();
        when(findGameByIdPort.findGameById(any())).thenReturn(
                mockGame(playerId, GamePhase.FIRST_EIGHT_CARDS_ARE_DEALT, true));

        var result = dealCardsService.dealCards(null, playerId);

        assertThat(result.size()).isEqualTo(14);
        verify(savePlayerPort).savePlayer(any());
    }

    @Test
    void dealCards_playerNotInGame_exception() {
        var playerId = UUID.randomUUID();
        when(findGameByIdPort.findGameById(any())).thenReturn(
                mockGame(playerId, GamePhase.DEALING_CARDS, false));

        assertThatThrownBy(() -> dealCardsService.dealCards(null, UUID.randomUUID()))
                .isInstanceOf(IllegalStateException.class);
    }

    private static Game mockGame(UUID playerId, GamePhase gamePhase, boolean firstEightCardsReceived) {
        return new Game.Builder(null, gamePhase)
                .firstTeam(new Team.Builder(null)
                        .firstPlayer(new Player.Builder(playerId)
                                .firstEightCardsReceived(firstEightCardsReceived)
                                .cards(Collections.nCopies(14, Card.DOG))
                                .build())
                        .build())
                .build();
    }
}