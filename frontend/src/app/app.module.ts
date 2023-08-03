import {NgModule} from '@angular/core'
import {BrowserModule} from '@angular/platform-browser'

import {AppRoutingModule} from './app-routing.module'
import {AppComponent} from './app.component'
import {ApiModule, Configuration, ConfigurationParameters} from './tichu/api'
import {HttpClientModule} from '@angular/common/http'

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
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
