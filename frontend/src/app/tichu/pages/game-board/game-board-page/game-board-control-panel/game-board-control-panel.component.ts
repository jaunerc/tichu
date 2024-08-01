import { Component, Input } from '@angular/core'
import { GameBoardWebsocketService } from '../service/game-board-websocket.service'
import { ControlPanelState } from '../model/control-panel-state'

@Component({
  selector: 'tichu-game-board-control-panel',
  templateUrl: './game-board-control-panel.component.html',
  styleUrls: ['./game-board-control-panel.component.scss']
})
export class GameBoardControlPanelComponent {
  @Input() gameId!: string
  @Input() playerId!: string
  @Input() controlPanelState: ControlPanelState

  constructor (
    private readonly websocketService: GameBoardWebsocketService
  ) {
    this.controlPanelState = 'INITIAL'
  }

  callGrandTichu (): void {
    this.sendGrandTichu(true)
  }

  dontCallGrandTichu (): void {
    this.sendGrandTichu(false)
  }

  callSmallTichu (): void {
    this.sendSmallTichu(true)
  }

  dontCallSmallTichu (): void {
    this.sendSmallTichu(false)
  }

  private sendGrandTichu (callGrandTichu: boolean = false): void {
    this.websocketService.publishGrandTichu(this.gameId, this.playerId, callGrandTichu)
  }

  private sendSmallTichu (callSmallTichu: boolean = false): void {
    this.websocketService.publishSmallTichu(this.gameId, this.playerId, callSmallTichu)
  }
}
