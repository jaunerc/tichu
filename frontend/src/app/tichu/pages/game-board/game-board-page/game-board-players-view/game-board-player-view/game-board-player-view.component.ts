import { Component, Input } from '@angular/core'

@Component({
  selector: 'tichu-game-board-player-view',
  templateUrl: './game-board-player-view.component.html',
  styleUrls: ['./game-board-player-view.component.scss']
})
export class GameBoardPlayerViewComponent {
  @Input() playerName: string = ''
  @Input() seatId: string = ''
}
