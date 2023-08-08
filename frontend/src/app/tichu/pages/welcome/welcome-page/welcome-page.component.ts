import { Component, OnInit } from '@angular/core'
import { PlayersService } from '../../../api'
import { Observable } from 'rxjs'
import { FormControl, Validators } from '@angular/forms'
import { Store } from '@ngrx/store'
import { saveUsername } from '../../../states/app/app.actions'
import { getUsername } from '../../../states/app/app.selector'
import { Router } from '@angular/router'

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

  public username$: Observable<string | undefined> | undefined

  constructor (
    private readonly store: Store,
    private readonly router: Router,
    private readonly playersService: PlayersService
  ) {
  }

  ngOnInit (): void {
    // this.player$ = this.playersService.createPlayer('Champion')
    this.username$ = this.store.select(getUsername)
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
