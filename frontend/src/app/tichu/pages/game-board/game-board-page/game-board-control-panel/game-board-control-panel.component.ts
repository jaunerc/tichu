import { Component, Input } from '@angular/core'
import { GameBoardWebsocketService } from '../service/game-board-websocket.service'
import { ControlPanelState } from '../model/control-panel-state'
import { TichuCallResult } from '../../../../websocket-api/model/tichuCallResult'

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

  onGrandTichuClick (tichuCallResult: TichuCallResult): void {
    this.sendTichuCall(tichuCallResult)
  }

  onSmallTichuClick (tichuCallResult: TichuCallResult): void {
    this.sendTichuCall(tichuCallResult)
  }

  private sendTichuCall (tichuCallResult: TichuCallResult): void {
    this.websocketService.publishTichuCall(this.gameId, this.playerId, tichuCallResult)
  }
}
