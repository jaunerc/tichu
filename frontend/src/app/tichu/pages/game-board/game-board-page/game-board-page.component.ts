import { Component, OnInit } from '@angular/core'
import { Store } from '@ngrx/store'
import { StompService } from '../../../stomp/stomp.service'
import { combineLatest, first, map, mergeMap, Observable, Subject, withLatestFrom } from 'rxjs'
import { getGameId, getGameState, getPlayerId } from '../../../states/app/app.selector'
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy'
import { DealCardsResponseMessage } from '../../../websocket-api/websocket.api'
import { refreshGameState } from '../../../states/app/app.actions'

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
    private readonly stompService: StompService
  ) {
  }

  ngOnInit (): void {
    this.gameId$ = this.store.select(getGameId)
    this.playerId$ = this.store.select(getPlayerId)

    this.onDealCardsResponse()
    this.onGameStateResponse()
    this.requestDealCards()

    this.store.dispatch(refreshGameState())
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
        this.stompService.publish({
          destination: '/app/' + gameId + '/deal-cards/' + playerId
        })
      })
  }

  private onDealCardsResponse (): void {
    combineLatest([this.gameId$, this.playerId$])
      .pipe(
        first(),
        untilDestroyed(this),
        mergeMap(([gameId, playerId]) => {
          return this.stompService.watch('/topic/' + gameId + '/deal-cards/' + playerId)
        }))
      .subscribe(message => {
        const dealCardsResponse: DealCardsResponseMessage = JSON.parse(message.body)
        this.cardsSubject$.next(dealCardsResponse.cards)
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
