import { AppState } from './tichu/states/app/app.state'
import { ActionReducerMap } from '@ngrx/store'
import { initialAppState, reducer } from './tichu/states/app/app.reducer'

export interface TichuState {
  app?: AppState
}

export const initialState: TichuState = {
  app: initialAppState
}

export const reducers: ActionReducerMap<TichuState> = {
  app: reducer
}
