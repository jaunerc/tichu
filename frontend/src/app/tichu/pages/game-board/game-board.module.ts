import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { SharedModule } from '../../shared/shared.module'
import { GameBoardPageComponent } from './game-board-page/game-board-page.component'

@NgModule({
  declarations: [
    GameBoardPageComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ]
})
export class GameBoardModule { }
