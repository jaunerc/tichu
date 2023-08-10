import { StompService } from './stomp.service'
import { rxStompConfig } from '../../../rx-stomp.config'

export function StompFactory (): StompService {
  const stompService = new StompService()
  stompService.configure(rxStompConfig)
  stompService.activate()
  return stompService
}
