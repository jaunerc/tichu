import { NgModule } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser'

import { AppRoutingModule } from './app-routing.module'
import { AppComponent } from './app.component'
import { ApiModule, Configuration, ConfigurationParameters } from './tichu/api'
import { HttpClientModule } from '@angular/common/http'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { StoreModule } from '@ngrx/store'
import { initialState, reducers } from './reducers'
import { StoreDevtoolsModule } from '@ngrx/store-devtools'
import { StompService } from './tichu/stomp/stomp.service'
import { StompFactory } from './tichu/stomp/stomp.factory'

export function apiConfiguration (): Configuration {
  const params: ConfigurationParameters = {
    basePath: 'http://localhost:8080/api'
  }
  return new Configuration(params)
}

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ApiModule.forRoot(apiConfiguration),
    HttpClientModule,
    BrowserAnimationsModule,
    StoreModule.forRoot(reducers, {
      initialState
    }),
    StoreDevtoolsModule.instrument({
      name: 'NgRx Tichu DevTools'
    })
  ],
  providers: [
    {
      provide: StompService,
      useFactory: StompFactory
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
