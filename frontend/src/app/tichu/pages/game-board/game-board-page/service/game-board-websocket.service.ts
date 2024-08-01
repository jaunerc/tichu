import { Injectable } from '@angular/core'
import { StompService } from '../../../../stomp/stomp.service'
import { TichuCallResult } from '../../../../websocket-api/model/tichuCallResult'

@Injectable({
  providedIn: 'root'
})
export class GameBoardWebsocketService {
  constructor (
    private readonly stompService: StompService
  ) { }

  publishTichuCall (gameId: string, playerId: string, tichuCallResult: TichuCallResult): void {
    this.stompService.publish({
      destination: `/app/${gameId}/tichu-call/${playerId}`,
      body: JSON.stringify({ tichuCall: tichuCallResult })
    })
  }

  publishRequestCards (gameId: string, playerId: string): void {
    this.stompService.publish({
      destination: `/app/${gameId}/deal-cards/${playerId}`
    })
  }
}
