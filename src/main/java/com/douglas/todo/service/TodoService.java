package com.douglas.todo.service;

import com.douglas.todo.model.Todo;
import com.douglas.todo.repository.TodoRepository;
import com.douglas.todo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService{

    @Autowired
    private TodoRepository repository;



    public Todo findById(Long id){
        Optional<Todo> obj = repository.findById(id);
        return  obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! id: " +id+",type: "+Todo.class.getName()));
    }

  public List<Todo> findAllOpen(){
        List<Todo> list = repository.findAllOpen();
        return list;
    }

     public List<Todo> findAllClose() {
        List<Todo> list = repository.findAllClose();
        return list;
    }

    public List<Todo> findAll() {
        List<Todo> list = repository.findAll();
        return list;
    }

    public Todo create(Todo obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Todo update(Long id,Todo obj) {
        Todo newObj = findById(id);
        newObj.setDescricao(obj.getDescricao());
        newObj.setTitulo(obj.getTitulo());
        newObj.setDataFinalizar(obj.getDataFinalizar());
        newObj.setFinalizada(obj.isFinalizada());
        return repository.save(newObj);
    }
}
