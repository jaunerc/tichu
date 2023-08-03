import { Component, OnInit } from '@angular/core'
import { Player, PlayersService } from '../../../api'
import { Observable } from 'rxjs'

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.scss']
})
export class WelcomePageComponent implements OnInit {
  public player$!: Observable<Player>

  constructor (
    private readonly playersService: PlayersService
  ) {
  }

  ngOnInit (): void {
    this.player$ = this.playersService.createPlayer('Champion')
  }
}
