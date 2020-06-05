import { ListTodoService } from 'src/app/services/list-todo.service';
import { TodoModel } from './../model/todo.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-single-todo',
  templateUrl: './single-todo.component.html',
  styleUrls: ['./single-todo.component.css']
})
export class SingleTodoComponent implements OnInit {

  public todo: TodoModel;

  constructor(private todoService: ListTodoService) { }

  ngOnInit(): void {
    this.todo = this.todoService.getTodo();
  }

}
