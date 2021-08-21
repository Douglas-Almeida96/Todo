import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Todo } from 'src/app/models/todo';
import { TodoService } from 'src/app/services/todo.service';

@Component({
  selector: 'app-read-all',
  templateUrl: './read-all.component.html',
  styleUrls: ['./read-all.component.css']
})
export class ReadAllComponent implements OnInit {
  close = 0;

  list: Todo[] = [];
  listFiniched: Todo[] = [];

  constructor(private service : TodoService, private router: Router) { }

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.service.findAll().subscribe(resposta =>{
      resposta.forEach(todo => {
        if(todo.finalizada){
          this.listFiniched.push(todo);
        }else{
          this.list.push(todo);
        }
      })
      this.close = this.listFiniched.length;
    })
  }
  finalizar(item: Todo):void{
    item.finalizada= true;
    this.service.update(item).subscribe(()=>{
      this.service.message('Task finalized with succcess!');
      this.list = this.list.filter(todo => todo.id !== item.id)
    });
  }

 delete(id: any):void{
    this.service.delete(id).subscribe((resposta)=>{
      if(resposta === null){
        this.service.message('Task deleted with succcess!');
        this.list = this.list.filter(todo => todo.id !== id)
        this.close++;
      }
    })
 }

 navegarParaFinalizados():void{
    this.router.navigate(['finalizados']);
 }
}
