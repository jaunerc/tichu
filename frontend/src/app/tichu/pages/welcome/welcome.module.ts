import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {WelcomePageComponent} from './welcome-page/welcome-page.component';
import {WelcomeRoutingModule} from "./welcome-routing.module";


@NgModule({
  declarations: [
    WelcomePageComponent
  ],
  imports: [
    CommonModule,
    WelcomeRoutingModule,
  ]
})
export class WelcomeModule {
}
