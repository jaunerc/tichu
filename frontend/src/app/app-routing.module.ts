import { NgModule, Type } from '@angular/core'
import { Route, RouterModule } from '@angular/router'
import { WelcomeModule } from './tichu/pages/welcome/welcome.module'

export type RoutePath =
  | ''

export interface TichuRoute extends Route {
  path: RoutePath
}

const routes: TichuRoute[] = [
  {
    path: '',
    loadChildren: async (): Promise<Type<WelcomeModule>> =>
      await import('./tichu/pages/welcome/welcome.module').then((m) => m.WelcomeModule)
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
