import { Component, OnInit } from '@angular/core'
import { Store } from '@ngrx/store'
import { combineLatest, first, map, Observable, Subject, withLatestFrom } from 'rxjs'
import { getGameId, getGameState, getPlayerId, getPlayerPrivateState } from '../../../states/app/app.selector'
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy'
import { refreshGameState, refreshPlayerPrivateState } from '../../../states/app/app.actions'
import { GameBoardWebsocketService } from './service/game-board-websocket.service'

export interface ControlPanelIds {
  gameId: string
  playerId: string
}

@UntilDestroy()
@Component({
  selector: 'tichu-game-board-page',
  templateUrl: './game-board-page.component.html',
  styleUrls: ['./game-board-page.component.scss']
})
export class GameBoardPageComponent implements OnInit {
  gameId$!: Observable<string>
  private playerId$!: Observable<string>

  private readonly cardsSubject$: Subject<string[]> = new Subject<string[]>()
  cards$ = this.cardsSubject$.asObservable()

  grandTichuCalledPlayers: string = ''

  constructor (
    private readonly store: Store,
    private readonly websocketService: GameBoardWebsocketService
  ) {
  }

  ngOnInit (): void {
    this.gameId$ = this.store.select(getGameId)
    this.playerId$ = this.store.select(getPlayerId)

    this.store.dispatch(refreshGameState())
    this.store.dispatch(refreshPlayerPrivateState())

    this.onPlayerPrivateStateResponse()
    this.onGameStateResponse()
    this.requestDealCards()
  }

  controlPanelIds$ (): Observable<ControlPanelIds> {
    return this.gameId$.pipe(
      withLatestFrom(this.playerId$),
      map(([gameId, playerId]) => {
        return { gameId, playerId }
      }))
  }

  private requestDealCards (): void {
    combineLatest([this.gameId$, this.playerId$])
      .pipe(
        first(),
        untilDestroyed(this))
      .subscribe(([gameId, playerId]) => {
        this.websocketService.publishRequestCards(gameId, playerId)
      })
  }

  private onPlayerPrivateStateResponse (): void {
    this.store.select(getPlayerPrivateState)
      .pipe(
        untilDestroyed(this),
        map((playerPrivateState) => {
          if (playerPrivateState != null) {
            return playerPrivateState.cards
          }
          return []
        }))
      .subscribe(cards => {
        this.cardsSubject$.next(cards)
      })
  }

  private onGameStateResponse (): void {
    this.store.select(getGameState)
      .pipe(
        untilDestroyed(this),
        map(gameState => {
          if (gameState != null) {
            return gameState.players?.map(player => player.grandTichuCalled)
          }
          return []
        })
      )
      .subscribe(grandTichus => {
        this.grandTichuCalledPlayers = `${grandTichus.filter(grandTichu => grandTichu).length}`
      })
  }
}
