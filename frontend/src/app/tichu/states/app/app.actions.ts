import {createAction, props} from '@ngrx/store'

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
