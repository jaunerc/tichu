import { Component, OnInit } from '@angular/core'
import { Player, PlayersService } from '../../../api'
import { Observable } from 'rxjs'
import { FormControl, Validators } from '@angular/forms'
import { Store } from '@ngrx/store'
import { saveUsername } from '../../../states/app/app.actions'
import {getUsername} from "../../../states/app/app.selector";

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
    private readonly playersService: PlayersService
  ) {
  }

  ngOnInit (): void {
    // this.player$ = this.playersService.createPlayer('Champion')
    this.username$ = this.store.select(getUsername)
  }

  submitUsername (): void {
    if (this.usernameControl.valid) {
      this.store.dispatch(saveUsername({ username: this.usernameControl.value }))
      console.log('form control value: ' + this.usernameControl.value)
    }
  }
}
