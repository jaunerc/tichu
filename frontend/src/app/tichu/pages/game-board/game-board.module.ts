import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { SharedModule } from '../../shared/shared.module'
import { GameBoardPageComponent } from './game-board-page/game-board-page.component'
import { GameBoardRoutingModule } from './game-board-routing.module'
import { MatButtonModule } from '@angular/material/button'
import {
  GameBoardControlPanelComponent
} from './game-board-page/game-board-control-panel/game-board-control-panel.component'

@NgModule({
  declarations: [
    GameBoardPageComponent,
    GameBoardControlPanelComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    GameBoardRoutingModule,
    MatButtonModule
  ]
})
export class GameBoardModule { }
