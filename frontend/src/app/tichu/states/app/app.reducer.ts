import { Action, ActionCreator, createReducer, on, ReducerTypes } from '@ngrx/store'
import { saveUsername } from './app.actions'
import { AppState } from './app.state'

export const initialAppState: AppState = {
  username: undefined
}

export const generalAppReducers: Array<ReducerTypes<AppState, ActionCreator[]>> = [
  on(saveUsername, (appState, payload) => ({
    ...appState,
    username: payload.username
  }))
]

const appReducer = createReducer<AppState>(
  initialAppState,
  ...generalAppReducers
)

export function reducer (state: AppState | undefined, action: Action): AppState {
  return appReducer(state, action)
}
