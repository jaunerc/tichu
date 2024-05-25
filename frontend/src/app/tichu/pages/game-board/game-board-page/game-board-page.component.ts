import { Component, OnInit } from '@angular/core'
import { Store } from '@ngrx/store'
import { combineLatest, map, Observable, Subject, withLatestFrom } from 'rxjs'
import {
  getGameId,
  getGameState,
  getPlayerId,
  getPlayerPrivateState,
  getPlayerSeatId
} from '../../../states/app/app.selector'
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy'
import { refreshGameState, refreshPlayerPrivateState } from '../../../states/app/app.actions'
import { GameBoardWebsocketService } from './service/game-board-websocket.service'
import { GameState, PlayerSeatId } from '../../../states/app/app.state'
import { filterUndefinedOrNull, filterUndefinedOrNullForCombinedValues } from '../../../states/type-util'

export interface ControlPanelIds {
  gameId?: string
  playerId?: string
}

@UntilDestroy()
@Component({
  selector: 'tichu-game-board-page',
  templateUrl: './game-board-page.component.html',
  styleUrls: ['./game-board-page.component.scss']
})
export class GameBoardPageComponent implements OnInit {
  gameId$!: Observable<string | undefined>
  playerSeatId$!: Observable<PlayerSeatId | undefined>
  private playerId$!: Observable<string | undefined>

  private readonly gameStateSubject$: Subject<GameState> = new Subject<GameState>()
  gameState$ = this.gameStateSubject$.asObservable()

  private readonly cardsSubject$: Subject<string[]> = new Subject<string[]>()
  cards$ = this.cardsSubject$.asObservable()

  constructor (
    private readonly store: Store,
    private readonly websocketService: GameBoardWebsocketService
  ) {
  }

  ngOnInit (): void {
    this.gameId$ = this.store.select(getGameId)
    this.playerId$ = this.store.select(getPlayerId)
    this.playerSeatId$ = this.store.select(getPlayerSeatId)

    this.store.dispatch(refreshGameState())
    this.store.dispatch(refreshPlayerPrivateState())

    this.onPlayerPrivateStateResponse()
    this.onGameStateResponse()
    this.requestDealCards()
  }

  private requestDealCards (): void {
    combineLatest([this.gameId$, this.playerId$])
      .pipe(
        untilDestroyed(this),
        filterUndefinedOrNullForCombinedValues())
      .subscribe(([gameId, playerId]) => {
        this.websocketService.publishRequestCards(gameId, playerId)
      })
  }

  private onPlayerPrivateStateResponse (): void {
    this.store.select(getPlayerPrivateState)
      .pipe(
        untilDestroyed(this),
        filterUndefinedOrNull(),
        map((playerPrivateState) => {
          return playerPrivateState.cards
        }))
      .subscribe(cards => {
        this.cardsSubject$.next(cards)
      })
  }

  private onGameStateResponse (): void {
    this.store.select(getGameState)
      .pipe(
        untilDestroyed(this),
        filterUndefinedOrNull()
      )
      .subscribe(gameState => {
        this.gameStateSubject$.next(gameState)
      })
  }

  controlPanelIds$ (): Observable<ControlPanelIds> {
    return this.gameId$.pipe(
      withLatestFrom(this.playerId$),
      map(([gameId, playerId]) => {
        return { gameId, playerId }
      }))
  }
}
