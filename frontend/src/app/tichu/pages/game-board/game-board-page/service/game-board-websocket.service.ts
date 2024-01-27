import { Injectable } from '@angular/core'
import { StompService } from '../../../../stomp/stomp.service'

@Injectable({
  providedIn: 'root'
})
export class GameBoardWebsocketService {
  constructor (
    private readonly stompService: StompService
  ) { }

  publishGrandTichu (gameId: string, playerId: string, callGrandTichu: boolean): void {
    this.stompService.publish({
      destination: `/app/${gameId}/grand-tichu/${playerId}`,
      body: JSON.stringify({ callGrandTichu })
    })
  }

  publishRequestCards (gameId: string, playerId: string): void {
    this.stompService.publish({
      destination: `/app/${gameId}/deal-cards/${playerId}`
    })
  }
}
