import { TodoModel } from '../model/todo.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  singleTodo: TodoModel;
  constructor(private http: HttpClient) { }

  public listOfTodo(): Observable<TodoModel[]>{
    return this.http.get<TodoModel[]>('http://localhost:8080/todo');
  }

  public getSingleTodo(id: number): Observable<TodoModel>{
    return this.http.get<TodoModel>(`http://localhost:8080/todo/${id}`);
  }

  public updateTodo(id: number, body: TodoModel){
    return this.http.put(`http://localhost:8080/todo/${id}`, body);
  }
}
