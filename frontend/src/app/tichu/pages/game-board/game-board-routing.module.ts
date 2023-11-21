import { NgModule } from '@angular/core'
import { RouterModule, Routes } from '@angular/router'
import { GameBoardPageComponent } from './game-board-page/game-board-page.component'

const routes: Routes = [
  {
    path: '',
    component: GameBoardPageComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GameBoardRoutingModule { }
