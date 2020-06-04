import { TodoModel } from './../model/todo.model';

import { Component, OnInit } from '@angular/core';
import { ListTodoService } from '../services/list-todo.service';

@Component({
  selector: 'app-list-todo',
  templateUrl: './list-todo.component.html',
  styleUrls: ['./list-todo.component.css']
})
export class ListTodoComponent implements OnInit {

  todos: TodoModel[];

  constructor(private todoList: ListTodoService) { }

  ngOnInit(): void {
    this.todoList.listOfTodo('https://jsonplaceholder.typicode.com/todos').subscribe(
      (responce) => this.todos = responce
    );
  }

}
