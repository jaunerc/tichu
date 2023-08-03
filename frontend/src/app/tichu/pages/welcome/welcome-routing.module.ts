import { RouterModule, Routes } from '@angular/router'
import { WelcomePageComponent } from './welcome-page/welcome-page.component'
import { NgModule } from '@angular/core'

const routes: Routes = [
  {
    path: '',
    component: WelcomePageComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WelcomeRoutingModule {
}
