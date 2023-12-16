import { Actions, createEffect, ofType } from '@ngrx/effects'
import { inject } from '@angular/core'
import { StompService } from '../../stomp/stomp.service'
import { exhaustMap, map, mergeMap, of } from 'rxjs'
import { getGameId } from './app.selector'
import { Store } from '@ngrx/store'
import { refreshGameState, saveGameState } from './app.actions'
import { GameStateServerMessage } from '../../websocket-api/websocket.api'

export const refreshGameStateEffect = createEffect(
  (actions$ = inject(Actions), stompService = inject(StompService), store = inject(Store)) => {
    return actions$.pipe(
      ofType(refreshGameState),
      mergeMap(() => store.select(getGameId)),
      exhaustMap((gameId) => {
        if (gameId != null) {
          return stompService.watch(`/topic/${gameId}/state`).pipe(
            map(message => {
              const gameStateServerMessage: GameStateServerMessage = JSON.parse(message.body)
              return saveGameState(({ gameState: gameStateServerMessage.game }))
            })
          )
        }
        return of()
      }
      ))
  },
  { functional: true }
)
