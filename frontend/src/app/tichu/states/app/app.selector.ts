import { createFeatureSelector, createSelector } from '@ngrx/store'
import { AppState } from './app.state'
import { valueIfPresentOrError } from '../type-util'

export const getAppState = createFeatureSelector<AppState>('app')

export const getUsername = createSelector(
  getAppState,
  (state) => state.username
)

export const getUserId = createSelector(
  getAppState,
  (state) => valueIfPresentOrError(state.userId)
)

export const getGameId = createSelector(
  getAppState,
  (state) => state.gameId
)

export const getPlayerId = createSelector(
  getAppState,
  (state) => state.playerId
)

export const getPlayerSeatId = createSelector(
  getAppState,
  (state) => state.playerSeatId
)

export const getGameState = createSelector(
  getAppState,
  (state) => state.gameState
)

export const getPlayerPrivateState = createSelector(
  getAppState,
  (state) => state.playerPrivateState
)
