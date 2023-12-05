package ch.jaunerc.tichu.backend.websocket.message.game;

import ch.jaunerc.tichu.backend.domain.game.model.GamePhase;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class GamePhaseDtoConverter {

    public static GamePhaseDto convert(GamePhase gamePhase) {
        switch (gamePhase) {
            case PLAYERS_JOINING -> {
                return GamePhaseDto.PLAYERS_JOINING;
            }
            case DEALING_CARDS -> {
                return GamePhaseDto.DEALING_CARDS;
            }
            case FIRST_EIGHT_CARDS_ARE_DEALT -> {
                return GamePhaseDto.FIRST_EIGHT_CARDS_ARE_DEALT;
            }
            case ALL_CARDS_ARE_DEALT -> {
                return GamePhaseDto.ALL_CARDS_ARE_DEALT;
            }
            case GAME_IS_RUNNING -> {
                return GamePhaseDto.GAME_IS_RUNNING;
            }
            case GAME_ENDED -> {
                return GamePhaseDto.GAME_ENDED;
            }
        }

        throw new IllegalStateException("Cannot convert the game phase=" + gamePhase);
    }
}
