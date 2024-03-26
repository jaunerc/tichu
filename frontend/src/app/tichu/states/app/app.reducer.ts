import { Action, ActionCreator, createReducer, on, ReducerTypes } from '@ngrx/store'
import {
  saveGameId,
  saveGameState,
  savePlayerId,
  savePlayerPrivateState,
  savePlayerSeatId,
  saveUserId,
  saveUsername
} from './app.actions'
import { AppState } from './app.state'

export const initialAppState: AppState = {
  username: undefined,
  userId: undefined,
  gameId: undefined,
  playerId: undefined,
  playerSeatId: undefined,
  gameState: undefined,
  playerPrivateState: undefined
}

export const generalAppReducers: Array<ReducerTypes<AppState, ActionCreator[]>> = [
  on(saveUsername, (appState, payload) => ({
    ...appState,
    username: payload.username
  })),
  on(saveUserId, (appState, payload) => ({
    ...appState,
    userId: payload.userId
  })),
  on(saveGameId, (appState, payload) => ({
    ...appState,
    gameId: payload.gameId
  })),
  on(savePlayerId, (appState, payload) => ({
    ...appState,
    playerId: payload.playerId
  })),
  on(savePlayerSeatId, (appState, payload) => ({
    ...appState,
    playerSeatId: payload.playerSeatId
  })),
  on(saveGameState, (appState, payload) => ({
    ...appState,
    gameState: payload.gameState
  })),
  on(savePlayerPrivateState, (appState, payload) => ({
    ...appState,
    playerPrivateState: payload.playerPrivateState
  }))
]

const appReducer = createReducer<AppState>(
  initialAppState,
  ...generalAppReducers
)

export function reducer (state: AppState | undefined, action: Action): AppState {
  return appReducer(state, action)
}
