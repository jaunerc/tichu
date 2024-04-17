import { Component, Input } from '@angular/core'
import { PlayerSeatId, PlayerState } from '../../../../states/app/app.state'

@Component({
  selector: 'tichu-game-board-players-view',
  templateUrl: './game-board-players-view.component.html',
  styleUrls: ['./game-board-players-view.component.scss']
})
export class GameBoardPlayersViewComponent {
  @Input() players: PlayerState[] = []
  @Input() myPlayerSeatId!: PlayerSeatId

  getMyPlayerState (): PlayerState {
    return this.getPlayerStateBySeatId(this.myPlayerSeatId)
  }

  getMyPartnerPlayerState (): PlayerState {
    const myPlayerState = this.getMyPlayerState()
    const partnerPlayerState = this.players
      .filter(playerState => playerState.teamIdentifier === myPlayerState.teamIdentifier)
      .find(playerState => playerState.playerSeatId !== myPlayerState.playerSeatId)
    if (partnerPlayerState != null) {
      return partnerPlayerState
    }
    throw new Error('cannot find the player state of my partner')
  }

  getLeftPlayerState (): PlayerState {
    const sortedPlayersBySeatId = this.sortByPlayerSeatId()
    const myPlayerSeatIndex = sortedPlayersBySeatId.findIndex((player) => player.playerSeatId === this.myPlayerSeatId)
    return sortedPlayersBySeatId[this.modWithNegValues(myPlayerSeatIndex - 1, 4)]
  }

  getRightPlayerState (): PlayerState {
    const sortedPlayersBySeatId = this.sortByPlayerSeatId()
    const myPlayerSeatIndex = sortedPlayersBySeatId.findIndex((player) => player.playerSeatId === this.myPlayerSeatId)
    return sortedPlayersBySeatId[this.modWithNegValues(myPlayerSeatIndex + 1, 4)]
  }

  modWithNegValues (n: number, m: number): number {
    return ((n % m) + m) % m
  }

  private getPlayerStateBySeatId (playerSeatId: string): PlayerState {
    const playerStateOrUndefined = this.players.find(playerState => playerState.playerSeatId === playerSeatId)
    if (playerStateOrUndefined != null) {
      return playerStateOrUndefined
    }
    throw new Error('cannot find the player state for the playerSeatId=' + playerSeatId)
  }

  private sortByPlayerSeatId (): PlayerState[] {
    const seatOrder: PlayerSeatId[] = ['FIRST', 'SECOND', 'THIRD', 'FOURTH']
    return [...this.players].sort((p1, p2) => seatOrder.indexOf(p1.playerSeatId) - seatOrder.indexOf(p2.playerSeatId))
  }
}
