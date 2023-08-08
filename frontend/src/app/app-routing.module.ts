import { NgModule, Type } from '@angular/core'
import { Route, RouterModule } from '@angular/router'
import { WelcomeModule } from './tichu/pages/welcome/welcome.module'
import { LobbyModule } from './tichu/pages/lobby/lobby.module'

export type RoutePath =
  | ''
  | 'lobby'

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
      await import('./tichu/pages/lobby/lobby.module').then((m) => m.LobbyModule)
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
