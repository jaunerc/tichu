import { ComponentFixture, TestBed } from '@angular/core/testing'

import { GameElement, LobbyPageComponent } from './lobby-page.component'
import { provideMockStore } from '@ngrx/store/testing'
import { getUsername } from '../../../states/app/app.selector'
import { Game, GamesService } from '../../../api'
import { anyString, anything, instance, mock, verify, when } from 'ts-mockito'
import { of } from 'rxjs'
import { SharedModule } from '../../../shared/shared.module'
import { Component, Input } from '@angular/core'
import { Router } from '@angular/router'

@Component({
  selector: 'tichu-lobby-game-overview',
  template: ''
})
class MockLobbyGameOverview {
  @Input() gamesDataSource: GameElement[] = []
}

@Component({
  selector: 'tichu-lobby-control-pane',
  template: ''
})
class MockTichuLobbyControlPane {
  @Input() selectedGameId?: string
}

describe('LobbyPageComponent', () => {
  let component: LobbyPageComponent
  let fixture: ComponentFixture<LobbyPageComponent>
  let gamesService: GamesService
  let router: Router

  beforeEach(() => {
    gamesService = mock(GamesService)
    router = mock(Router)
    when(gamesService.getGames()).thenReturn(of({ games: [] }))

    TestBed.configureTestingModule({
      imports: [SharedModule],
      declarations: [
        LobbyPageComponent,
        MockLobbyGameOverview,
        MockTichuLobbyControlPane],
      providers: [
        provideMockStore({
          selectors: [
            { selector: getUsername, value: 'Arthur' }
          ]
        }),
        { provide: GamesService, useValue: instance(gamesService) },
        { provide: Router, useValue: instance(router) }
      ]
    })
    fixture = TestBed.createComponent(LobbyPageComponent)
    component = fixture.componentInstance
    fixture.detectChanges()
  })

  it('should show the username in the title', () => {
    const html: HTMLElement = fixture.nativeElement
    const header: HTMLHeadingElement | null = html.querySelector('h1')

    expect(header?.textContent).toContain('Arthur')
  })

  it('should update the components game list again when creating a new game', () => {
    when(gamesService.createGame()).thenReturn(of({ id: '1' } satisfies Game))
    when(gamesService.getGames()).thenReturn(of({ games: [{ id: '1' }, { id: '2' }] }))

    component.onCreateGame()

    verify(gamesService.createGame()).once()
    verify(gamesService.getGames()).twice()
  })

  it('should navigate to the game-loader page when successfully joining a game', () => {
    when(gamesService.joinGame(anyString(), anyString())).thenReturn(of({ gameId: '1', playerId: '42' }))
    component.selectedGame = { id: '1' }
    component.userId$ = of('42')

    component.onJoinGame()

    verify(router.navigate(anything())).once()
  })
})
