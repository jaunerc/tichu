import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'tichu-lobby-control-pane',
  templateUrl: './lobby-control-pane.component.html',
  styleUrls: ['./lobby-control-pane.component.scss']
})
export class LobbyControlPaneComponent {

  @Input() selectedGameId?: string

  @Output() createGame = new EventEmitter<void>()
  @Output() joinGame = new EventEmitter<void>()

  public isNoGameSelected(): boolean {
    return this.selectedGameId === undefined
  }
}
