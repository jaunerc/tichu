import { Component, OnInit } from '@angular/core'
import { Store } from '@ngrx/store'
import { StompService } from '../../../stomp/stomp.service'
import { combineLatest, first, mergeMap, Observable, of, Subject } from 'rxjs'
import { getGameId, getPlayerId } from '../../../states/app/app.selector'
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy'
import { DealCardsResponseMessage, GameStateServerMessage } from '../../../websocket-api/websocket.api'

@UntilDestroy()
@Component({
  selector: 'tichu-game-board-page',
  templateUrl: './game-board-page.component.html',
  styleUrls: ['./game-board-page.component.scss']
})
export class GameBoardPageComponent implements OnInit {
  private gameId$!: Observable<string | undefined>
  private playerId$!: Observable<string | undefined>

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
  }

  public callGrandTichu (): void {
    combineLatest([this.gameId$, this.playerId$])
      .pipe(
        first(),
        untilDestroyed(this))
      .subscribe(([gameId, playerId]) => {
        if (gameId != null && playerId != null) {
          this.stompService.publish({
            destination: '/app/' + gameId + '/grand-tichu/' + playerId,
            body: JSON.stringify({ callGrandTichu: true })
          })
        }
      })
  }

  public dontCallGrandTichu (): void {
    combineLatest([this.gameId$, this.playerId$])
      .pipe(
        first(),
        untilDestroyed(this))
      .subscribe(([gameId, playerId]) => {
        if (gameId != null && playerId != null) {
          this.stompService.publish({
            destination: '/app/' + gameId + '/grand-tichu/' + playerId,
            body: JSON.stringify({ callGrandTichu: false })
          })
        }
      })
  }

  private requestDealCards (): void {
    combineLatest([this.gameId$, this.playerId$])
      .pipe(
        first(),
        untilDestroyed(this))
      .subscribe(([gameId, playerId]) => {
        if (gameId != null && playerId != null) {
          this.stompService.publish({
            destination: '/app/' + gameId + '/deal-cards/' + playerId
          })
        }
      })
  }

  private onDealCardsResponse (): void {
    combineLatest([this.gameId$, this.playerId$])
      .pipe(
        first(),
        untilDestroyed(this),
        mergeMap(([gameId, playerId]) => {
          if (gameId != null && playerId != null) {
            return this.stompService.watch('/topic/' + gameId + '/deal-cards/' + playerId)
          }
          return of()
        }))
      .subscribe(message => {
        const dealCardsResponse: DealCardsResponseMessage = JSON.parse(message.body)
        this.cardsSubject$.next(dealCardsResponse.cards)
      })
  }

  private onGameStateResponse (): void {
    combineLatest([this.gameId$, this.playerId$])
      .pipe(
        first(),
        untilDestroyed(this),
        mergeMap(([gameId, playerId]) => {
          if (gameId != null && playerId != null) {
            return this.stompService.watch('/topic/' + gameId + '/state')
          }
          return of()
        }))
      .subscribe(message => {
        const grandTichuServerMessage: GameStateServerMessage = JSON.parse(message.body)

        this.grandTichuCalledPlayers = `${grandTichuServerMessage.game.players.filter(player => player.grandTichuCalled).length}`
      })
  }
}
