import { Actions, createEffect, ofType } from '@ngrx/effects'
import { inject } from '@angular/core'
import { StompService } from '../../stomp/stomp.service'
import { exhaustMap, map, mergeMap, switchMap, withLatestFrom } from 'rxjs'
import { getGameId, getPlayerId } from './app.selector'
import { Store } from '@ngrx/store'
import { refreshGameState, refreshPlayerPrivateState, saveGameState, savePlayerPrivateState } from './app.actions'
import { mapToGameState } from './app.state.mapper'
import { filterUndefinedOrNull, filterUndefinedOrNullForCombinedValues } from '../type-util'
import { GameStateServerMessage } from '../../websocket-api/model/gameStateServerMessage'
import { PlayerPrivateStateServerMessage } from '../../websocket-api/model/playerPrivateStateServerMessage'

export const refreshGameStateEffect = createEffect(
  (actions$ = inject(Actions), stompService = inject(StompService), store = inject(Store)) => {
    return actions$.pipe(
      ofType(refreshGameState),
      mergeMap(() => store.select(getGameId)),
      filterUndefinedOrNull(),
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
          withLatestFrom(store.select(getGameId))
        )
      }),
      filterUndefinedOrNullForCombinedValues(),
      switchMap(([playerId, gameId]) => {
        return stompService.watch(`/topic/${gameId}/state/${playerId}`).pipe(
          map(message => {
            const playerPrivateStateServerMessage: PlayerPrivateStateServerMessage = JSON.parse(message.body)
            return savePlayerPrivateState(({ playerPrivateState: playerPrivateStateServerMessage.playerPrivateState }))
          })
        )
      }
      ))
  },
  { functional: true }
)
