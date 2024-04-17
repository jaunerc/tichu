export interface AppState {
  username: string | undefined
  userId: string | undefined
  gameId: string | undefined
  playerId: string | undefined
  playerSeatId: PlayerSeatId | undefined
  gameState: GameState | undefined
  playerPrivateState: PlayerPrivateState | undefined
}

export interface GameState {
  players: PlayerState[]
  gamePhase: GamePhaseState
}

export interface PlayerState {
  name: string
  grandTichuCalled: boolean
  smallTichuCalled: boolean
  playerSeatId: PlayerSeatId
  teamIdentifier: TeamIdentifierState
}

export type PlayerSeatId = 'FIRST' | 'SECOND' | 'THIRD' | 'FOURTH'

export type GamePhaseState = 'PLAYERS_JOINING' | 'DEALING_CARDS' | 'FIRST_EIGHT_CARDS_ARE_DEALT' | 'ALL_CARDS_ARE_DEALT' | 'GAME_IS_RUNNING' | 'GAME_ENDED'

export type TeamIdentifierState = 'FIRST_TEAM' | 'SECOND_TEAM'

export interface PlayerPrivateState {
  cards: string[]
}
