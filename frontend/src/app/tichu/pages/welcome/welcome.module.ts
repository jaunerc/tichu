import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { WelcomePageComponent } from './welcome-page/welcome-page.component'
import { WelcomeRoutingModule } from './welcome-routing.module'
import {MatToolbarModule} from "@angular/material/toolbar";

@NgModule({
  declarations: [
    WelcomePageComponent
  ],
  imports: [
    CommonModule,
    WelcomeRoutingModule,
    MatToolbarModule
  ]
})
export class WelcomeModule {
}
