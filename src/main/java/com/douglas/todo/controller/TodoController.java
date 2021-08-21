package com.douglas.todo.controller;

import com.douglas.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.douglas.todo.service.TodoService;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/todos")
public class TodoController{

    @Autowired
    TodoService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Todo> findById(@PathVariable("id") Long id){
        Todo obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/open")
    public ResponseEntity<List<Todo>> listOpen(){
        List<Todo> lista = service.findAllOpen();
        return ResponseEntity.ok().body(lista);
    }
   @GetMapping(value = "/close")
    public ResponseEntity<List<Todo>> listClose(){
        List<Todo> lista = service.findAllClose();
        return ResponseEntity.ok().body(lista);
    }
    @GetMapping()
    public ResponseEntity<List<Todo>> listAll(){
        List<Todo> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping()
    public ResponseEntity<Todo> create(@RequestBody Todo obj) {
        obj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Todo> update(@PathVariable("id") Long id, @RequestBody Todo obj){
        Todo newObj = service.update(id, obj);
        return ResponseEntity.ok().body(newObj);
    }
}
