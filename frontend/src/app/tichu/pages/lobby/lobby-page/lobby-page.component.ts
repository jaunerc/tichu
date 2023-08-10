import { Component, OnInit } from '@angular/core'
import { Store } from '@ngrx/store'
import { Observable } from 'rxjs'
import { getUsername } from '../../../states/app/app.selector'
import { Games, GamesService } from '../../../api'

@Component({
  selector: 'tichu-lobby-page',
  templateUrl: './lobby-page.component.html',
  styleUrls: ['./lobby-page.component.scss']
})
export class LobbyPageComponent implements OnInit {
  public username$?: Observable<string | undefined>
  public games$?: Observable<Games>

  constructor (
    private readonly store: Store,
    private readonly gamesService: GamesService
  ) {}

  ngOnInit (): void {
    this.username$ = this.store.select(getUsername)
    this.games$ = this.gamesService.getGames()
  }
}
