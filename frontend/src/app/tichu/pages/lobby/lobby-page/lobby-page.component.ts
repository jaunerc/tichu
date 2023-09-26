import {Component, OnInit} from '@angular/core'
import {Store} from '@ngrx/store'
import {first, Observable, Subject} from 'rxjs'
import {getUserId, getUsername} from '../../../states/app/app.selector'
import {Games, GamesService} from '../../../api'
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy'
import {saveGameId, savePlayerId} from "../../../states/app/app.actions";

export interface GameElement {
  id: string
}

@UntilDestroy()
@Component({
  selector: 'tichu-lobby-page',
  templateUrl: './lobby-page.component.html',
  styleUrls: ['./lobby-page.component.scss']
})
export class LobbyPageComponent implements OnInit {
  public username$!: Observable<string | undefined>
  public userId$!: Observable<string | undefined>
  private readonly gamesSubject$: Subject<Games> = new Subject<Games>()
  public selectedGame?: GameElement
  public dataSource: GameElement[] = []

  constructor(
    private readonly store: Store,
    private readonly gamesService: GamesService
  ) {
  }

  public ngOnInit(): void {
    this.username$ = this.store.select(getUsername)
    this.userId$ = this.store.select(getUserId)
    this.updateGames()

    this.gamesSubject$
      .pipe(untilDestroyed(this))
      .subscribe(games => {
        this.dataSource = games.games
          ?.map(game => {
            return {id: game.id} satisfies GameElement
          })
      })
  }

  public onCreateGame(): void {
    this.gamesService.createGame()
      .pipe(first(), untilDestroyed(this))
      .subscribe(() => {
        this.updateGames()
      })
  }

  public onGameSelected(selectedGame: GameElement): void {
    this.selectedGame = selectedGame
  }

  public onJoinGame(): void {
    this.userId$
      .pipe(first(), untilDestroyed(this))
      .subscribe(userId => {
        if (this.selectedGame && userId) {
          const selectedGameId = this.selectedGame.id
          void this.gamesService.joinGame(
            selectedGameId,
            userId
          ).forEach(joinGame => {
            this.store.dispatch(saveGameId({gameId: selectedGameId}))
            this.store.dispatch(savePlayerId(({playerId: joinGame.playerId})))
          })
        }
      })
  }

  private updateGames(): void {
    void this.gamesService.getGames()
      .forEach(games => {
        this.gamesSubject$.next(games)
      })
  }
}
