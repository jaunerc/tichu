package ch.jaunerc.tichu.backend.domain.game;

import ch.jaunerc.tichu.backend.domain.game.model.Game;
import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import ch.jaunerc.tichu.backend.domain.game.model.Player;
import ch.jaunerc.tichu.backend.domain.game.model.Team;
import ch.jaunerc.tichu.backend.domain.game.model.card.Card;
import ch.jaunerc.tichu.backend.domain.game.port.FindGameByIdPort;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DealCardsServiceTest {

    @Mock
    private FindGameByIdPort findGameByIdPort;
    @InjectMocks
    private DealCardsService dealCardsService;

    @Test
    void dealCards_firstDealingRound_onlyEightCards() {
        var playerId = UUID.randomUUID();
        when(findGameByIdPort.findGameById(any())).thenReturn(
                mockGame(playerId, GamePhase.DEALING_CARDS));

        var result = dealCardsService.dealCards(null, playerId);

        assertThat(result.size()).isEqualTo(8);
    }

    @Test
    void dealCards_secondDealingRound_allCards() {
        var playerId = UUID.randomUUID();
        when(findGameByIdPort.findGameById(any())).thenReturn(
                mockGame(playerId, GamePhase.FIRST_EIGHT_CARDS_ARE_DEALT));

        var result = dealCardsService.dealCards(null, playerId);

        assertThat(result.size()).isEqualTo(14);
    }

    @Test
    void dealCards_invalidGamePhase_exception() {
        var playerId = UUID.randomUUID();
        when(findGameByIdPort.findGameById(any())).thenReturn(
                mockGame(playerId, GamePhase.ALL_CARDS_ARE_DEALT));

        assertThatThrownBy(() -> dealCardsService.dealCards(null, playerId))
                .isInstanceOf(IllegalStateException.class);
    }

    private static Game mockGame(UUID playerId, GamePhase gamePhase) {
        return new Game.Builder(null, gamePhase)
                .firstTeam(new Team.Builder(null)
                        .firstPlayer(new Player.Builder(playerId)
                                .cards(Collections.nCopies(14, Card.DOG))
                                .build())
                        .build())
                .build();
    }
}