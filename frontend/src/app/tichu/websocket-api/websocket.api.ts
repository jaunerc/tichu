// TODO these types should be share with the backend
export interface ReadyStatusMessage {
  readyPlayers: number
}

export interface DealCardsResponseMessage {
  cards: string[]
}
export interface GameStateServerMessage {
  game: GameDto
}

export interface GameDto {
  players: PlayerDto[]
  gamePhase: GamePhaseDto
}

export interface PlayerDto {
  grandTichuCalled: boolean
  smallTichuCalled: boolean
  teamIdentifierDto: TeamIdentifierDto
}

export enum TeamIdentifierDto {
  FIRST_TEAM = 'FIRST_TEAM',
  SECOND_TEAM = 'SECOND_TEAM'
}

export enum GamePhaseDto {
  PLAYERS_JOINING = 'PLAYERS_JOINING',
  DEALING_CARDS = 'DEALING_CARDS',
  FIRST_EIGHT_CARDS_ARE_DEALT = 'FIRST_EIGHT_CARDS_ARE_DEALT',
  ALL_CARDS_ARE_DEALT = 'ALL_CARDS_ARE_DEALT',
  GAME_IS_RUNNING = 'GAME_IS_RUNNING',
  GAME_ENDED = 'GAME_ENDED'
}

export interface PlayerPrivateMessage {
  privateState: PlayerPrivateDto
}

export interface PlayerPrivateDto {
  cards: string[]
}
