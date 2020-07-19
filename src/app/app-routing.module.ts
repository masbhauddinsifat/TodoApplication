import { TodoComponent } from './todo/todo.component';
import { SingleTodoComponent } from './single-todo/single-todo.component';
import { ListTodoComponent } from './list-todo/list-todo.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';


const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'todo', component: ListTodoComponent},
  {path: 'todo/:id', component: SingleTodoComponent},
  {path: 'todo/:id/edit', component: TodoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
