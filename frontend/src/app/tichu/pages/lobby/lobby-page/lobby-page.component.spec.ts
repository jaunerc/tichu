import { ComponentFixture, TestBed } from '@angular/core/testing'

import { LobbyPageComponent } from './lobby-page.component'
import { provideMockStore } from '@ngrx/store/testing'
import { getUsername } from '../../../states/app/app.selector'

describe('LobbyPageComponent', () => {
  let component: LobbyPageComponent
  let fixture: ComponentFixture<LobbyPageComponent>

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LobbyPageComponent],
      providers: [
        provideMockStore({
          selectors: [
            { selector: getUsername, value: 'Arthur' }
          ]
        })
      ]
    })
    fixture = TestBed.createComponent(LobbyPageComponent)
    component = fixture.componentInstance
    fixture.detectChanges()
  })

  it('should create', () => {
    expect(component).toBeTruthy()
  })
})
