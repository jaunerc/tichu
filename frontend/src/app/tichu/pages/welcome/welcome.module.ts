import {NgModule} from '@angular/core'
import {CommonModule} from '@angular/common'
import {WelcomePageComponent} from './welcome-page/welcome-page.component'
import {WelcomeRoutingModule} from './welcome-routing.module'
import {MatToolbarModule} from '@angular/material/toolbar'
import {MatInputModule} from '@angular/material/input'
import {MatButtonModule} from '@angular/material/button'
import {ReactiveFormsModule} from '@angular/forms'

@NgModule({
  declarations: [
    WelcomePageComponent
  ],
  imports: [
    CommonModule,
    WelcomeRoutingModule,
    MatToolbarModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule
  ]
})
export class WelcomeModule {
}
