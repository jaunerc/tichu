import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { SharedModule } from '../../shared/shared.module'
import { GameBoardPageComponent } from './game-board-page/game-board-page.component'
import { GameBoardRoutingModule } from './game-board-routing.module'

@NgModule({
  declarations: [
    GameBoardPageComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    GameBoardRoutingModule
  ]
})
export class GameBoardModule { }
