import { Injectable } from '@angular/core'
import { StompService } from '../../../../stomp/stomp.service'
import { map, Observable } from 'rxjs'
import { ReadyStatusServerMessage } from '../../../../websocket-api/model/readyStatusServerMessage'

@Injectable({
  providedIn: 'root'
})
export class GameLoaderWebsocketService {
  constructor (
    private readonly stompService: StompService
  ) { }

  watchOnReadyMessage (gameId: string): Observable<ReadyStatusServerMessage> {
    return this.stompService.watch(`/topic/player-ready-${gameId}`)
      .pipe(map(message => this.parseReadyStatusMessage(message.body)))
  }

  publishReadyMessage (gameId: string, playerId: string): void {
    this.stompService.publish({
      destination: `/app/player-ready-${gameId}`,
      body: JSON.stringify({ playerId })
    })
  }

  private parseReadyStatusMessage (json: string): ReadyStatusServerMessage {
    return JSON.parse(json)
  }
}
