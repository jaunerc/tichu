import { Component, OnInit } from '@angular/core'
import { Store } from '@ngrx/store'
import { getGameId, getPlayerId } from '../../../states/app/app.selector'
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy'
import { combineLatest, first, mergeMap, Observable, Subject } from 'rxjs'
import { Router } from '@angular/router'
import { GameLoaderWebsocketService } from './service/game-loader-websocket.service'

@UntilDestroy()
@Component({
  selector: 'tichu-game-loader-page',
  templateUrl: './game-loader-page.component.html',
  styleUrls: ['./game-loader-page.component.scss']
})
export class GameLoaderPageComponent implements OnInit {
  private gameId$!: Observable<string>
  private playerId$!: Observable<string>
  private readonly readyPlayersSubject$: Subject<number> = new Subject<number>()

  readyPlayers$: Observable<number> = this.readyPlayersSubject$.asObservable()

  constructor (
    private readonly websocketService: GameLoaderWebsocketService,
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
          return this.websocketService.watchOnReadyMessage(gameId)
        }))
      .subscribe(readyStatus => {
        const playerCount = readyStatus.readyPlayers
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
        this.websocketService.publishReadyMessage(gameId, playerId)
      })
  }
}
