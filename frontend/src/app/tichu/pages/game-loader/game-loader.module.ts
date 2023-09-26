import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {GameLoaderPageComponent} from './game-loader-page/game-loader-page.component';
import {GameLoaderRoutingModule} from "./game-loader-routing.module";

@NgModule({
  declarations: [
    GameLoaderPageComponent,
  ],
  imports: [
    CommonModule,
    GameLoaderRoutingModule,
  ]
})
export class GameLoaderModule { }
