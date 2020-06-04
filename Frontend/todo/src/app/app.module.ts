import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { CreateTodoComponent } from './create-todo/create-todo.component';
import { ListTodoComponent } from './list-todo/list-todo.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { SingleTodoComponent } from './single-todo/single-todo.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CreateTodoComponent,
    ListTodoComponent,
    HomeComponent,
    SingleTodoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
