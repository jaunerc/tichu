import { createAction, props } from '@ngrx/store'
import { GameState, PlayerPrivateState } from './app.state'

export const saveUsername = createAction(
  '[App] save username',
  props<{ username: string }>())

export const saveUserId = createAction(
  '[App] save user id',
  props<{ userId: string }>()
)

export const saveGameId = createAction(
  '[App] save game id',
  props<{ gameId: string }>()
)

export const savePlayerId = createAction(
  '[App] save player id',
  props<{ playerId: string }>()
)

export const savePlayerSeatId = createAction(
  '[App] save player seat id',
  props<{ playerSeatId: string }>()
)

export const refreshGameState = createAction(
  '[App] refresh game state'
)

export const saveGameState = createAction(
  '[App] save game state',
  props<{ gameState: GameState }>()
)

export const refreshPlayerPrivateState = createAction(
  '[App] refresh player private state'
)

export const savePlayerPrivateState = createAction(
  '[App] save player private state',
  props<{ playerPrivateState: PlayerPrivateState }>()
)
