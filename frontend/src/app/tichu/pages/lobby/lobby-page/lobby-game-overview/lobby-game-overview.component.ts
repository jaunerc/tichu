import {Component, EventEmitter, Input, Output} from '@angular/core';
import {GameElement} from "../lobby-page.component";

@Component({
  selector: 'tichu-lobby-game-overview',
  templateUrl: './lobby-game-overview.component.html',
  styleUrls: ['./lobby-game-overview.component.scss']
})
export class LobbyGameOverviewComponent {

  @Input() public gamesDataSource: GameElement[] = []
  @Output() public gameSelected = new EventEmitter<GameElement>()

  private selectedRow?: GameElement

  public selectRow(row: GameElement): void {
    this.selectedRow = row
    this.gameSelected.emit(this.selectedRow)
  }

  public isCurrentRowSelected(currentRow: GameElement): boolean {
    return currentRow.id === this.selectedRow?.id
  }
}
