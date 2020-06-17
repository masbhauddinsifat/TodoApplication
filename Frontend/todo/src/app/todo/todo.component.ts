import { TodoModel } from '../model/todo.model';
import { TodoService } from 'src/app/services/todo.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {
  id: number;
  public todo: TodoModel;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private todoService: TodoService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.id = parseInt(params.get('id'), 10);
    });

    this.todo = new TodoModel(0, '', false, new Date());
    this.todoService.getSingleTodo(this.id).subscribe((responce) => {
      this.todo = responce;
    });
  }

  onSave(){
    let responceValue: any;
    // console.log(JSON.stringify(this.todo));
    this.todoService.updateTodo(this.id, this.todo).subscribe(
      (responce) => {
        responceValue = responce;
    });

    this.router.navigate([{id: this.id}], {relativeTo: this.route.parent});
  }

  goBack(){
    this.router.navigate([{id: this.id}], {relativeTo: this.route.parent});
  }

}
