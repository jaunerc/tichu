import { AppState } from './tichu/states/app/app.state'
import { ActionReducerMap } from '@ngrx/store'
import { reducer } from './tichu/states/app/app.reducer'

export interface TichuState {
  app?: AppState
}

export const reducers: ActionReducerMap<TichuState> = {
  app: reducer
}
