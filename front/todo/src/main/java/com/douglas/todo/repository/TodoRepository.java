package com.douglas.todo.repository;

import com.douglas.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
   /* List<Todo> findByfinalizadaFalseOrderBydataFinalizarDesc();

    List<Todo> findByfinalizadaTrueOrderBydataFinalizar();*/

    @Query("SELECT obj FROM Todo obj WHERE obj.finalizada = false ORDER BY obj.dataFinalizar")
    List<Todo> findAllOpen();

    @Query("SELECT obj FROM Todo obj WHERE obj.finalizada = True ORDER BY obj.dataFinalizar")
    List<Todo> findAllClose();
}
