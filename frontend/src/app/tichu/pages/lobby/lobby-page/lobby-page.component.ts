import { Component, OnInit } from '@angular/core'
import { Store } from '@ngrx/store'
import { Observable, Subject } from 'rxjs'
import { getUsername } from '../../../states/app/app.selector'
import { Games, GamesService } from '../../../api'
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy'

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
  public username$?: Observable<string | undefined>
  private readonly gamesSubject$: Subject<Games> = new Subject<Games>()

  public dataSource: GameElement[] = []

  constructor (
    private readonly store: Store,
    private readonly gamesService: GamesService
  ) {}

  public ngOnInit (): void {
    this.username$ = this.store.select(getUsername)
    this.updateGames()

    this.gamesSubject$
      .pipe(untilDestroyed(this))
      .subscribe(games => {
        this.dataSource = games.games
          ?.map(game => {
            return { id: game.id } satisfies GameElement
          })
      })
  }

  public createGame (): void {
    this.gamesService.createGame()
      .pipe(untilDestroyed(this))
      .subscribe(() => {
        this.updateGames()
      })
  }

  private updateGames (): void {
    void this.gamesService.getGames()
      .forEach(games => {
        this.gamesSubject$.next(games)
      })
  }
}
