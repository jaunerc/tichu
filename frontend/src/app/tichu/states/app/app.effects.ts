import { Actions, createEffect, ofType } from '@ngrx/effects'
import { inject } from '@angular/core'
import { StompService } from '../../stomp/stomp.service'
import { exhaustMap, first, map, mergeMap, switchMap, withLatestFrom } from 'rxjs'
import { getGameId, getPlayerId } from './app.selector'
import { Store } from '@ngrx/store'
import { refreshGameState, refreshPlayerPrivateState, saveGameState, savePlayerPrivateState } from './app.actions'
import { GameStateServerMessage, PlayerPrivateMessage } from '../../websocket-api/websocket.api'
import { mapToGameState, mapToPlayerPrivateState } from './app.state.mapper'

export const refreshGameStateEffect = createEffect(
  (actions$ = inject(Actions), stompService = inject(StompService), store = inject(Store)) => {
    return actions$.pipe(
      ofType(refreshGameState),
      mergeMap(() => store.select(getGameId)),
      exhaustMap((gameId) => {
        return stompService.watch(`/topic/${gameId}/state`).pipe(
          map(message => {
            const gameStateServerMessage: GameStateServerMessage = JSON.parse(message.body)
            return saveGameState(({ gameState: mapToGameState(gameStateServerMessage.game) }))
          })
        )
      }
      ))
  },
  { functional: true }
)

export const refreshPlayerPrivateStateEffect = createEffect(
  (actions$ = inject(Actions), stompService = inject(StompService), store = inject(Store)) => {
    return actions$.pipe(
      ofType(refreshPlayerPrivateState),
      mergeMap(() => {
        return store.select(getPlayerId).pipe(
          first(),
          withLatestFrom(store.select(getGameId))
        )
      }),
      switchMap(([playerId, gameId]) => {
        return stompService.watch(`/topic/${gameId}/state/${playerId}`).pipe(
          map(message => {
            const playerPrivateStateServerMessage: PlayerPrivateMessage = JSON.parse(message.body)
            return savePlayerPrivateState(({ playerPrivateState: mapToPlayerPrivateState(playerPrivateStateServerMessage.playerPrivateState) }))
          })
        )
      }
      ))
  },
  { functional: true }
)
