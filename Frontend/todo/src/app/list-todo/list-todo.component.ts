import { Router, ActivatedRoute } from '@angular/router';
import { TodoModel } from './../model/todo.model';

import { Component, OnInit } from '@angular/core';
import { TodoService } from '../services/todo.service';

@Component({
  selector: 'app-list-todo',
  templateUrl: './list-todo.component.html',
  styleUrls: ['./list-todo.component.css']
})
export class ListTodoComponent implements OnInit {

  todos: TodoModel[];

  constructor(
      private todoService: TodoService,
      private router: Router,
      private route: ActivatedRoute
    ) { }

  ngOnInit(): void {
    this.todoService.listOfTodo().subscribe(
      (responce) => this.todos = responce
    );
  }

  goSingleTodoPage(todo: TodoModel){
    this.router.navigate([todo.id], {relativeTo: this.route});
  }

  goEditPage(todoId: number){
    const url = todoId + '/edit';
    this.router.navigate([url], {relativeTo: this.route});
  }

}
