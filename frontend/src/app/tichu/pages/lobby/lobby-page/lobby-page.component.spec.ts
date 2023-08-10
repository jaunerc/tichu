import { ComponentFixture, TestBed } from '@angular/core/testing'

import { LobbyPageComponent } from './lobby-page.component'
import { provideMockStore } from '@ngrx/store/testing'
import { getUsername } from '../../../states/app/app.selector'
import { GamesService } from '../../../api'
import { instance, mock } from 'ts-mockito'
import { Component } from '@angular/core'

@Component({
  selector: 'tichu-app-layout',
  template: ''
})
class TichuAppLayout {}

describe('LobbyPageComponent', () => {
  let component: LobbyPageComponent
  let fixture: ComponentFixture<LobbyPageComponent>
  let gamesService: GamesService

  beforeEach(() => {
    gamesService = mock(GamesService)

    TestBed.configureTestingModule({
      declarations: [LobbyPageComponent, TichuAppLayout],
      providers: [
        provideMockStore({
          selectors: [
            { selector: getUsername, value: 'Arthur' }
          ]
        }),
        { provide: GamesService, useValue: instance(gamesService) }
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
