import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { TodoService } from 'src/app/services/todo.service';
import { TodoModel } from './../model/todo.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-single-todo',
  templateUrl: './single-todo.component.html',
  styleUrls: ['./single-todo.component.css']
})
export class SingleTodoComponent implements OnInit {

  public todo: TodoModel;
  private todoId: number;

  constructor(
    private todoService: TodoService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    // this.todo = this.todoService.getTodo();
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.todoId = parseInt(params.get('id'), 10);
    });
    this.todo  = new TodoModel(0, '', false, new Date());
    this.todoService.getSingleTodo(this.todoId).subscribe((responce) => {
      this.todo = responce;
    });
  }

  moveToEditPage(){
    this.router.navigate(['edit'], {relativeTo: this.route});
  }

  onDelete(){
    this.todoService.deleteTodo(this.todoId).subscribe(
      response => {
         this.router.navigate([{id: this.todoId}], {relativeTo: this.route.parent});
        }
    );
  }
}
