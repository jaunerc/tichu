import { GameState, PlayerPrivateState, PlayerState } from './app.state'
import { GameDto, PlayerDto, PlayerPrivateDto } from '../../websocket-api/websocket.api'

export function mapToGameState (dto: GameDto): GameState {
  return {
    gamePhase: dto.gamePhase,
    players: dto.players.map(mapToPlayer)
  }
}

function mapToPlayer (dto: PlayerDto): PlayerState {
  return {
    name: dto.name,
    grandTichuCalled: dto.grandTichuCalled,
    smallTichuCalled: dto.smallTichuCalled,
    playerSeatId: dto.playerSeatId,
    teamIdentifier: dto.teamIdentifierDto
  }
}

export function mapToPlayerPrivateState (dto: PlayerPrivateDto): PlayerPrivateState {
  return {
    cards: dto.cards
  }
}
