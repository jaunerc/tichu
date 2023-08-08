import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { LobbyPageComponent } from './lobby-page/lobby-page.component'
import { LobbyRoutingModule } from './lobby-routing.module'
import { SharedModule } from '../../shared/shared.module'

@NgModule({
  declarations: [
    LobbyPageComponent
  ],
  imports: [
    CommonModule,
    LobbyRoutingModule,
    SharedModule
  ]
})
export class LobbyModule { }
