import { GameState, PlayerState } from './app.state'
import { Game } from '../../websocket-api/model/game'
import { Player } from '../../websocket-api/model/player'

export function mapToGameState (dto: Game): GameState {
  return {
    gamePhase: dto.gamePhase,
    players: dto.players.map(mapToPlayer)
  }
}

function mapToPlayer (dto: Player): PlayerState {
  return {
    name: dto.name,
    grandTichuCalled: dto.grandTichuCalled,
    smallTichuCalled: dto.smallTichuCalled,
    playerSeatId: dto.playerSeatId,
    teamIdentifier: dto.teamIdentifier
  }
}
