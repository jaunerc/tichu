import { Injectable } from '@angular/core'
import { StompService } from '../../../../stomp/stomp.service'
import { map, Observable } from 'rxjs'
import { DealCardsResponseMessage } from '../../../../websocket-api/websocket.api'

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

  watchOnDealCardsMessage (gameId: string, playerId: string): Observable<DealCardsResponseMessage> {
    return this.stompService.watch('/topic/' + gameId + '/deal-cards/' + playerId)
      .pipe(map(message => this.parseDealCardsMessage(message.body)))
  }

  private parseDealCardsMessage (json: string): DealCardsResponseMessage {
    return JSON.parse(json)
  }
}
