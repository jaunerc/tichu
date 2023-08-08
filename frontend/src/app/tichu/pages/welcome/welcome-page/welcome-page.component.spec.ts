import { ComponentFixture, TestBed } from '@angular/core/testing'
import { WelcomePageComponent } from './welcome-page.component'
import { provideMockStore } from '@ngrx/store/testing'
import { PlayersService } from '../../../api'
import { anyString, instance, mock, verify, when } from 'ts-mockito'
import { Component } from '@angular/core'
import { of } from 'rxjs'
import { getUsername } from '../../../states/app/app.selector'
import { ReactiveFormsModule } from '@angular/forms'
import { Router } from '@angular/router'

@Component({
  selector: 'mat-toolbar',
  template: ''
})
class MockMatToolbar {}

@Component({
  selector: 'mat-form-field',
  template: ''
})
class MockMatFormField {}

@Component({
  selector: 'mat-label',
  template: ''
})
class MockMatLabel {}

describe('WelcomePageComponent', () => {
  let component: WelcomePageComponent
  let fixture: ComponentFixture<WelcomePageComponent>
  let playersService: PlayersService
  let router: Router

  beforeEach(() => {
    playersService = mock(PlayersService)
    router = mock(Router)

    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule],
      declarations: [WelcomePageComponent, MockMatToolbar, MockMatFormField, MockMatLabel],
      providers: [
        provideMockStore({
          selectors: [
            { selector: getUsername, value: 'Arthur' }
          ]
        }),
        { provide: PlayersService, useValue: instance(playersService) },
        { provide: Router, useValue: instance(router) }
      ]
    })

    fixture = TestBed.createComponent(WelcomePageComponent)
    component = fixture.componentInstance
    fixture.detectChanges()
  })

  it('should send a create player request when the name is submitted', () => {
    component.usernameControl.setValue('Arthur')
    when(playersService.createPlayer(anyString())).thenReturn(of({ name: 'Arthur', id: '00000000-0000-0000-0000-000000000000' }))

    component.submitUsername()

    verify(playersService.createPlayer(anyString())).once()
  })

  it('should not send a create player request when no name is entered', () => {
    component.submitUsername()

    verify(playersService.createPlayer(anyString())).never()
  })
})
