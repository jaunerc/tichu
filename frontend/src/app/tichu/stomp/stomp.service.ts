import { RxStomp } from '@stomp/rx-stomp'
import { Injectable } from '@angular/core'

@Injectable({
  providedIn: 'root'
})
export class StompService extends RxStomp {
  // The constructor is needed for the correct initialisation of RxStomp
  // eslint-disable-next-line @typescript-eslint/no-useless-constructor
  constructor () {
    super()
  }
}
