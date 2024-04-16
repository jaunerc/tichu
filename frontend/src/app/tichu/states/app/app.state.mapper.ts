import {
  GamePhaseState,
  GameState,
  PlayerPrivateState,
  PlayerSeatId,
  PlayerState,
  TeamIdentifierState
} from './app.state'
import {
  GameDto,
  GamePhaseDto,
  PlayerDto,
  PlayerPrivateDto,
  PlayerSeatIdDto,
  TeamIdentifierDto
} from '../../websocket-api/websocket.api'

export function mapToGameState (dto: GameDto): GameState {
  return {
    gamePhase: mapToGamePhase(dto.gamePhase),
    players: dto.players.map(mapToPlayer)
  }
}

function mapToGamePhase (dto: GamePhaseDto): GamePhaseState {
  switch (dto) {
    case GamePhaseDto.PLAYERS_JOINING:
      return GamePhaseState.PLAYERS_JOINING
    case GamePhaseDto.DEALING_CARDS:
      return GamePhaseState.DEALING_CARDS
    case GamePhaseDto.FIRST_EIGHT_CARDS_ARE_DEALT:
      return GamePhaseState.FIRST_EIGHT_CARDS_ARE_DEALT
    case GamePhaseDto.ALL_CARDS_ARE_DEALT:
      return GamePhaseState.ALL_CARDS_ARE_DEALT
    case GamePhaseDto.GAME_IS_RUNNING:
      return GamePhaseState.GAME_IS_RUNNING
    case GamePhaseDto.GAME_ENDED:
      return GamePhaseState.GAME_ENDED
    default:
      // eslint-disable-next-line @typescript-eslint/restrict-template-expressions
      throw Error(`No mapper found for the value ${dto}`)
  }
}

function mapToPlayer (dto: PlayerDto): PlayerState {
  return {
    name: dto.name,
    grandTichuCalled: dto.grandTichuCalled,
    smallTichuCalled: dto.smallTichuCalled,
    playerSeatId: mapToPlayerSeatId(dto.playerSeatId),
    teamIdentifier: mapToTeamIdentifier(dto.teamIdentifierDto)
  }
}

function mapToTeamIdentifier (dto: TeamIdentifierDto): TeamIdentifierState {
  switch (dto) {
    case TeamIdentifierDto.FIRST_TEAM:
      return TeamIdentifierState.FIRST_TEAM
    case TeamIdentifierDto.SECOND_TEAM:
      return TeamIdentifierState.SECOND_TEAM
  }
}

function mapToPlayerSeatId (dto: PlayerSeatIdDto): PlayerSeatId {
  switch (dto) {
    case PlayerSeatIdDto.FIRST:
      return PlayerSeatId.FIRST
    case PlayerSeatIdDto.SECOND:
      return PlayerSeatId.SECOND
    case PlayerSeatIdDto.THIRD:
      return PlayerSeatId.THIRD
    case PlayerSeatIdDto.FOURTH:
      return PlayerSeatId.FOURTH
  }
}

export function mapToPlayerPrivateState (dto: PlayerPrivateDto): PlayerPrivateState {
  return {
    cards: dto.cards
  }
}
