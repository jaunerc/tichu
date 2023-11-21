import { Component, OnInit } from '@angular/core'
import { StompService } from '../../../stomp/stomp.service'
import { Store } from '@ngrx/store'
import { getGameId, getPlayerId } from '../../../states/app/app.selector'
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy'
import { combineLatest, first, mergeMap, Observable, of, Subject } from 'rxjs'
import { ReadyStatusDto } from '../../../websocket-api/websocket.api'
import { Router } from '@angular/router'

@UntilDestroy()
@Component({
  selector: 'tichu-game-loader-page',
  templateUrl: './game-loader-page.component.html',
  styleUrls: ['./game-loader-page.component.scss']
})
export class GameLoaderPageComponent implements OnInit {
  private gameId$!: Observable<string | undefined>
  private playerId$!: Observable<string | undefined>
  private readonly readyPlayersSubject$: Subject<number> = new Subject<number>()

  readyPlayers$: Observable<number> = this.readyPlayersSubject$.asObservable()

  constructor (private readonly stompService: StompService,
    private readonly store: Store,
    private readonly router: Router) {
  }

  ngOnInit (): void {
    this.gameId$ = this.store.select(getGameId)
    this.playerId$ = this.store.select(getPlayerId)

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
            return this.stompService.watch('/topic/player-ready-' + gameId)
          }
          return of()
        }))
      .subscribe(message => {
        const readyStatusDto: ReadyStatusDto = JSON.parse(message.body)
        const playerCount = readyStatusDto.readyPlayers
        this.readyPlayersSubject$.next(playerCount)

        if (playerCount === 4) {
          void this.router.navigate(['game-board'])
        }
      })
  }

  private sendReadyMessage (): void {
    combineLatest([this.gameId$, this.playerId$])
      .pipe(first(), untilDestroyed(this))
      .subscribe(([gameId, playerId]) => {
        if (gameId != null && playerId != null) {
          this.stompService.publish({
            destination: '/app/player-ready-' + gameId,
            body: JSON.stringify({ playerId })
          })
        }
      })
  }
}
