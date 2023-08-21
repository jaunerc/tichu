import { ComponentFixture, TestBed } from '@angular/core/testing'
import { WelcomePageComponent } from './welcome-page.component'
import { provideMockStore } from '@ngrx/store/testing'
import { UsersService } from '../../../api'
import { anyString, anything, instance, mock, verify, when } from 'ts-mockito'
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

@Component({
  selector: 'tichu-app-layout',
  template: ''
})
class MockTichuAppLayout {}

describe('WelcomePageComponent', () => {
  let component: WelcomePageComponent
  let fixture: ComponentFixture<WelcomePageComponent>
  let usersService: UsersService
  let router: Router

  beforeEach(() => {
    usersService = mock(UsersService)
    router = mock(Router)

    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule],
      declarations: [WelcomePageComponent, MockMatToolbar, MockMatFormField, MockMatLabel, MockTichuAppLayout],
      providers: [
        provideMockStore({
          selectors: [
            { selector: getUsername, value: 'Arthur' }
          ]
        }),
        { provide: UsersService, useValue: instance(usersService) },
        { provide: Router, useValue: instance(router) }
      ]
    })

    fixture = TestBed.createComponent(WelcomePageComponent)
    component = fixture.componentInstance
    fixture.detectChanges()
  })

  it('should send a create player request when the name is submitted and navigate', () => {
    component.usernameControl.setValue('Arthur')
    when(usersService.createUser(anyString())).thenReturn(of({ name: 'Arthur', id: '00000000-0000-0000-0000-000000000000' }))

    component.submitUsername()

    verify(usersService.createUser(anyString())).once()
    verify(router.navigate(anything())).once()
  })

  it('should not send a create player request when no name is entered', () => {
    component.submitUsername()

    verify(usersService.createUser(anyString())).never()
  })
})
