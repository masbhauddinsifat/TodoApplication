import { SingleTodoComponent } from './single-todo/single-todo.component';
import { ListTodoComponent } from './list-todo/list-todo.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';


const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'todos', component: ListTodoComponent},
  {path: 'todos/:id', component: SingleTodoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
