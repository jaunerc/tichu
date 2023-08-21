import { Component } from '@angular/core'
import { UsersService } from '../../../api'
import { FormControl, Validators } from '@angular/forms'
import { Store } from '@ngrx/store'
import { saveUsername } from '../../../states/app/app.actions'
import { Router } from '@angular/router'

@Component({
  selector: 'tichu-welcome-page',
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
    private readonly usersService: UsersService
  ) {
  }

  submitUsername (): void {
    if (this.usernameControl.valid) {
      this.usersService
        .createUser(this.usernameControl.value)
        .subscribe(user => {
          this.store.dispatch(saveUsername({ username: user.name }))
          void this.router.navigate(['lobby'])
        })
    }
  }
}
