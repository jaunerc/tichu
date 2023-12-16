import { GameDto } from '../../websocket-api/websocket.api'

export interface AppState {
  username: string | undefined
  userId: string | undefined
  gameId: string | undefined
  playerId: string | undefined
  gameState: GameDto | undefined
}
