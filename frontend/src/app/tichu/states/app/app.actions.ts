import { createAction, props } from '@ngrx/store'

export const saveUsername = createAction(
  '[App] save username',
  props<{ username: string }>())

export const saveUserId = createAction(
  '[App] save user id',
  props<{ userId: string }>()
)
