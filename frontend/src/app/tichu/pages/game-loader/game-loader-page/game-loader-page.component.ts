import { Component, OnInit } from '@angular/core'
import { StompService } from '../../../stomp/stomp.service'
import { Store } from '@ngrx/store'
import { getGameId } from '../../../states/app/app.selector'
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy'
import { first, mergeMap, Observable, of, Subject } from 'rxjs'
import { ReadyStatusDto } from '../../../websocket-api/websocket.api'

@UntilDestroy()
@Component({
  selector: 'tichu-game-loader-page',
  templateUrl: './game-loader-page.component.html',
  styleUrls: ['./game-loader-page.component.scss']
})
export class GameLoaderPageComponent implements OnInit {
  private gameId$!: Observable<string | undefined>
  private readonly readyPlayersSubject$: Subject<number> = new Subject<number>()

  readyPlayers$: Observable<number> = this.readyPlayersSubject$.asObservable()

  constructor (private readonly stompService: StompService,
    private readonly store: Store) {
  }

  ngOnInit (): void {
    this.gameId$ = this.store.select(getGameId)

    this.sendReadyMessage()

    this.onReadyMessage()
  }

  private onReadyMessage (): void {
    this.gameId$
      .pipe(
        first(),
        untilDestroyed(this),
        mergeMap(gameId => {
          if (gameId != null) {
            return this.stompService.watch('/topic/game-player-ready-' + gameId)
          }
          return of()
        }))
      .subscribe(message => {
        const playersCount: ReadyStatusDto = JSON.parse(message.body)
        this.readyPlayersSubject$.next(playersCount.readyPlayers)
      })
  }

  private sendReadyMessage (): void {
    this.gameId$
      .pipe(first(), untilDestroyed(this))
      .subscribe(gameId => {
        if (gameId != null) {
          this.stompService.publish({
            destination: '/app/game-player-ready-' + gameId,
            body: JSON.stringify({ playerId: '175f99fc-3e47-4970-aa8a-fb3333956503' })
          })
        }
      })
  }
}
