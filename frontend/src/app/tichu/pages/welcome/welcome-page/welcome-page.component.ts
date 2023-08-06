import { Component, OnInit } from '@angular/core'
import { Player, PlayersService } from '../../../api'
import { Observable } from 'rxjs'
import { FormControl, Validators } from '@angular/forms'

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.scss']
})
export class WelcomePageComponent implements OnInit {
  public usernameControl = new FormControl<string>('', {
    nonNullable: true,
    validators: Validators.required
  })

  public player$!: Observable<Player>

  constructor (
    private readonly playersService: PlayersService
  ) {
  }

  ngOnInit (): void {
    this.player$ = this.playersService.createPlayer('Champion')
  }

  submitUsername (): void {
    if (this.usernameControl.valid) {
      console.log('form control value: ' + this.usernameControl.value)
    }
  }
}
