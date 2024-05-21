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
  name: string
  grandTichuCalled: boolean
  smallTichuCalled: boolean
  playerSeatId: PlayerSeatIdDto
  teamIdentifierDto: TeamIdentifierDto
}

export type TeamIdentifierDto = 'FIRST_TEAM' | 'SECOND_TEAM'

export type PlayerSeatIdDto = 'FIRST' | 'SECOND' | 'THIRD' | 'FOURTH'

export type GamePhaseDto = 'PLAYERS_JOINING' | 'PLAYERS_ON_THE_TABLE' | 'DEALING_CARDS' | 'FIRST_EIGHT_CARDS_ARE_DEALT' | 'ALL_CARDS_ARE_DEALT' | 'GAME_IS_RUNNING' | 'GAME_ENDED'

export interface PlayerPrivateMessage {
  playerPrivateState: PlayerPrivateDto
}

export interface PlayerPrivateDto {
  cards: string[]
}
