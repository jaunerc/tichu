import { createFeatureSelector, createSelector } from '@ngrx/store'
import { AppState } from './app.state'

export const getAppState = createFeatureSelector<AppState>('app')

export const getUsername = createSelector(
  getAppState,
  (state) => state.username
)
