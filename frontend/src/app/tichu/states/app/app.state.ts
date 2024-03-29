export interface AppState {
  username: string | undefined
  userId: string | undefined
  gameId: string | undefined
  playerId: string | undefined
  gameState: GameState | undefined
  playerPrivateState: PlayerPrivateState | undefined
}

export interface GameState {
  players: PlayerState[]
  gamePhase: GamePhaseState
}

export interface PlayerState {
  grandTichuCalled: boolean
  smallTichuCalled: boolean
  teamIdentifier: TeamIdentifierState
}

export enum GamePhaseState {
  PLAYERS_JOINING = 'PLAYERS_JOINING',
  DEALING_CARDS = 'DEALING_CARDS',
  FIRST_EIGHT_CARDS_ARE_DEALT = 'FIRST_EIGHT_CARDS_ARE_DEALT',
  ALL_CARDS_ARE_DEALT = 'ALL_CARDS_ARE_DEALT',
  GAME_IS_RUNNING = 'GAME_IS_RUNNING',
  GAME_ENDED = 'GAME_ENDED'
}

export enum TeamIdentifierState {
  FIRST_TEAM = 'FIRST_TEAM',
  SECOND_TEAM = 'SECOND_TEAM'
}

export interface PlayerPrivateState {
  cards: string[]
}
