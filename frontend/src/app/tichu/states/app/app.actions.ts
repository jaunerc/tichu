import { createAction, props } from '@ngrx/store'

export const saveUsername = createAction(
  '[App] save username',
  props<{ username: string }>())
