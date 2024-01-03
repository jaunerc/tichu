import { Component, Input } from '@angular/core'
import { GameBoardWebsocketService } from '../service/game-board-websocket.service'

@Component({
  selector: 'tichu-game-board-control-panel',
  templateUrl: './game-board-control-panel.component.html',
  styleUrls: ['./game-board-control-panel.component.scss']
})
export class GameBoardControlPanelComponent {
  @Input() gameId!: string
  @Input() playerId!: string

  constructor (
    private readonly websocketService: GameBoardWebsocketService
  ) {
  }

  callGrandTichu (): void {
    this.sendGrandTichu(true)
  }

  dontCallGrandTichu (): void {
    this.sendGrandTichu(false)
  }

  private sendGrandTichu (callGrandTichu: boolean = false): void {
    this.websocketService.publishGrandTichu(this.gameId, this.playerId, callGrandTichu)
  }
}
