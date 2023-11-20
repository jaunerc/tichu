import { createFeatureSelector, createSelector } from '@ngrx/store'
import { AppState } from './app.state'

export const getAppState = createFeatureSelector<AppState>('app')

export const getUsername = createSelector(
  getAppState,
  (state) => state.username
)

export const getUserId = createSelector(
  getAppState,
  (state) => state.userId
)

export const getGameId = createSelector(
  getAppState,
  (state) => state.gameId
)
