import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { LobbyPageComponent } from './lobby-page/lobby-page.component'
import { LobbyRoutingModule } from './lobby-routing.module'

@NgModule({
  declarations: [
    LobbyPageComponent
  ],
  imports: [
    CommonModule,
    LobbyRoutingModule
  ]
})
export class LobbyModule { }
