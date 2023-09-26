import { ComponentFixture, TestBed } from '@angular/core/testing'

import { GameLoaderPageComponent } from './game-loader-page.component'
import { SharedModule } from '../../../shared/shared.module'

describe('GameLoaderPageComponent', () => {
  let component: GameLoaderPageComponent
  let fixture: ComponentFixture<GameLoaderPageComponent>

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [SharedModule],
      declarations: [GameLoaderPageComponent]
    })
    fixture = TestBed.createComponent(GameLoaderPageComponent)
    component = fixture.componentInstance
    fixture.detectChanges()
  })

  it('should create', () => {
    expect(component).toBeTruthy()
  })
})
