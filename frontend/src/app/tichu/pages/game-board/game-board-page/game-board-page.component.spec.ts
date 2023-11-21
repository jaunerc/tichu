import { ComponentFixture, TestBed } from '@angular/core/testing'

import { GameBoardPageComponent } from './game-board-page.component'

describe('GameBoardPageComponent', () => {
  let component: GameBoardPageComponent
  let fixture: ComponentFixture<GameBoardPageComponent>

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GameBoardPageComponent]
    })
    fixture = TestBed.createComponent(GameBoardPageComponent)
    component = fixture.componentInstance
    fixture.detectChanges()
  })

  it('should create', () => {
    expect(component).toBeTruthy()
  })
})
