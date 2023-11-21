import { RouterModule, Routes } from '@angular/router'
import { NgModule } from '@angular/core'
import { GameLoaderPageComponent } from './game-loader-page/game-loader-page.component'

const routes: Routes = [
  {
    path: '',
    component: GameLoaderPageComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GameLoaderRoutingModule {}
