import { Component } from '@angular/core'
import { PlayersService } from '../../../api'
import { FormControl, Validators } from '@angular/forms'
import { Store } from '@ngrx/store'
import { saveUsername } from '../../../states/app/app.actions'
import { Router } from '@angular/router'

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.scss']
})
export class WelcomePageComponent {
  public usernameControl = new FormControl<string>('', {
    nonNullable: true,
    validators: Validators.required
  })

  constructor (
    private readonly store: Store,
    private readonly router: Router,
    private readonly playersService: PlayersService
  ) {
  }

  submitUsername (): void {
    if (this.usernameControl.valid) {
      this.playersService
        .createPlayer(this.usernameControl.value)
        .subscribe(player => {
          this.store.dispatch(saveUsername({ username: player.name }))
          void this.router.navigate(['lobby'])
        })
    }
  }
}
