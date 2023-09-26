import {NgModule, Type} from '@angular/core'
import {Route, RouterModule} from '@angular/router'
import {WelcomeModule} from './tichu/pages/welcome/welcome.module'
import {LobbyModule} from './tichu/pages/lobby/lobby.module'
import {userRegisteredGuard} from './tichu/guards/user-registered.guard'
import {GameLoaderModule} from "./tichu/pages/game-loader/game-loader.module";

export type RoutePath =
  | ''
  | 'lobby'
  | 'game-loader'

export interface TichuRoute extends Route {
  path: RoutePath
}

const routes: TichuRoute[] = [
  {
    path: '',
    loadChildren: async (): Promise<Type<WelcomeModule>> =>
      await import('./tichu/pages/welcome/welcome.module').then((m) => m.WelcomeModule)
  },
  {
    path: 'lobby',
    loadChildren: async (): Promise<Type<LobbyModule>> =>
      await import('./tichu/pages/lobby/lobby.module').then((m) => m.LobbyModule),
    canActivate: [userRegisteredGuard]
  },
  {
    path: "game-loader",
    loadChildren: async (): Promise<Type<GameLoaderModule>> =>
      await import('./tichu/pages/game-loader/game-loader.module').then((m) => m.GameLoaderModule),
  },
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
