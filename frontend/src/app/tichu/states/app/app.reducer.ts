import { Action, ActionCreator, createReducer, on, ReducerTypes } from '@ngrx/store'
import { saveUserId, saveUsername } from './app.actions'
import { AppState } from './app.state'

export const initialAppState: AppState = {
  username: undefined,
  userId: undefined
}

export const generalAppReducers: Array<ReducerTypes<AppState, ActionCreator[]>> = [
  on(saveUsername, (appState, payload) => ({
    ...appState,
    username: payload.username
  })),
  on(saveUserId, (appState, payload) => ({
    ...appState,
    userId: payload.userId
  }))
]

const appReducer = createReducer<AppState>(
  initialAppState,
  ...generalAppReducers
)

export function reducer (state: AppState | undefined, action: Action): AppState {
  return appReducer(state, action)
}
