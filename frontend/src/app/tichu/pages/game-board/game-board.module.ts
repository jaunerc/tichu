import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { SharedModule } from '../../shared/shared.module'
import { GameBoardPageComponent } from './game-board-page/game-board-page.component'
import { GameBoardRoutingModule } from './game-board-routing.module'
import { MatButtonModule } from '@angular/material/button'
import {
  GameBoardControlPanelComponent
} from './game-board-page/game-board-control-panel/game-board-control-panel.component'
import {
  GameBoardPlayersViewComponent
} from './game-board-page/game-board-players-view/game-board-players-view.component'
import {
  GameBoardPlayerViewComponent
} from './game-board-page/game-board-players-view/game-board-player-view/game-board-player-view.component'

@NgModule({
  declarations: [
    GameBoardPageComponent,
    GameBoardControlPanelComponent,
    GameBoardPlayersViewComponent,
    GameBoardPlayerViewComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    GameBoardRoutingModule,
    MatButtonModule
  ]
})
export class GameBoardModule { }
