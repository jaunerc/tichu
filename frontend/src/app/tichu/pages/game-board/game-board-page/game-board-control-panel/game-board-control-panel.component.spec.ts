import { ComponentFixture, TestBed } from '@angular/core/testing'

import { GameBoardControlPanelComponent } from './game-board-control-panel.component'

describe('GameBoardControlPanelComponent', () => {
  let component: GameBoardControlPanelComponent
  let fixture: ComponentFixture<GameBoardControlPanelComponent>

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GameBoardControlPanelComponent]
    })
    fixture = TestBed.createComponent(GameBoardControlPanelComponent)
    component = fixture.componentInstance
    fixture.detectChanges()
  })

  it('should create', () => {
    expect(component).toBeTruthy()
  })
})
