import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { LobbyPageComponent } from './lobby-page/lobby-page.component'
import { LobbyRoutingModule } from './lobby-routing.module'
import { SharedModule } from '../../shared/shared.module'
import { MatCardModule } from '@angular/material/card'
import { MatTableModule } from '@angular/material/table'
import { MatButtonModule } from '@angular/material/button'

@NgModule({
  declarations: [
    LobbyPageComponent
  ],
  imports: [
    CommonModule,
    LobbyRoutingModule,
    SharedModule,
    MatCardModule,
    MatTableModule,
    MatButtonModule
  ]
})
export class LobbyModule { }
