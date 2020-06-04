import { TodoModel } from './../model/todo.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ListTodoService {

  constructor(private http: HttpClient) { }

  public listOfTodo(link: string):Observable<TodoModel[]>{
    return this.http.get<TodoModel[]>(link);
  }
}
