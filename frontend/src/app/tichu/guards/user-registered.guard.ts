import { CanActivateFn, Router } from '@angular/router'
import { inject } from '@angular/core'
import { Store } from '@ngrx/store'
import { getUsername } from '../states/app/app.selector'
import { map } from 'rxjs'

export const userRegisteredGuard: CanActivateFn = () => {
  const store = inject(Store)
  const router = inject(Router)
  return store
    .select(getUsername)
    .pipe(
      map((username) => !(username == null) ? true : router.parseUrl('/'))
    )
}
