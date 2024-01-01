import { Component, Input } from '@angular/core'
import { StompService } from '../../../../stomp/stomp.service'

@Component({
  selector: 'tichu-game-board-control-panel',
  templateUrl: './game-board-control-panel.component.html',
  styleUrls: ['./game-board-control-panel.component.scss']
})
export class GameBoardControlPanelComponent {
  @Input() gameId!: string
  @Input() playerId!: string

  constructor (
    private readonly stompService: StompService
  ) {
  }

  callGrandTichu (): void {
    this.sendGrandTichu(true)
  }

  dontCallGrandTichu (): void {
    this.sendGrandTichu(false)
  }

  private sendGrandTichu (callGrandTichu: boolean = false): void {
    this.stompService.publish({
      destination: '/app/' + this.gameId + '/grand-tichu/' + this.playerId,
      body: JSON.stringify({ callGrandTichu })
    })
  }
}
