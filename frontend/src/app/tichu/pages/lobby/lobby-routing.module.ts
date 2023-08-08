import { RouterModule, Routes } from '@angular/router'
import { LobbyPageComponent } from './lobby-page/lobby-page.component'
import { NgModule } from '@angular/core'

const routes: Routes = [
  {
    path: '',
    component: LobbyPageComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LobbyRoutingModule {}
