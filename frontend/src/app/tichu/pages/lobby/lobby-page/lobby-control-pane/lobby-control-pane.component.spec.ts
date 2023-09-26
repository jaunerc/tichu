import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LobbyControlPaneComponent} from './lobby-control-pane.component';
import {spy, verify} from "ts-mockito";

describe('LobbyControlPaneComponent', () => {
  let component: LobbyControlPaneComponent;
  let fixture: ComponentFixture<LobbyControlPaneComponent>;
  let buttons: NodeListOf<HTMLButtonElement>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LobbyControlPaneComponent]
    });
    fixture = TestBed.createComponent(LobbyControlPaneComponent);
    component = fixture.componentInstance;
    buttons = fixture.nativeElement.querySelectorAll('button')
    fixture.detectChanges();
  });

  it('should emit when the create game button is clicked', () => {
    const createGameSpy = spy(component.createGame)

    buttons.item(0).click()
    fixture.detectChanges()

    verify(createGameSpy.emit()).once()
  });

  it('should emit when the join game button is clicked', () => {
    const joinGameSpy = spy(component.joinGame)

    component.selectedGameId = '42'
    fixture.detectChanges()
    buttons.item(1).click()
    fixture.detectChanges()

    verify(joinGameSpy.emit()).once()
  });

  it('should show a disabled button when no game is selected', () => {
    expect(buttons.item(1).disabled).toBe(true)
  });
});
