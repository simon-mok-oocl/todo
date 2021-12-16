package com.example.todo_list.controller;

import com.example.todo_list.entity.Todo;
import com.example.todo_list.service.TodoService;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("todos")
@CrossOrigin

public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodo()
    {
        return this.todoService.findAll();
    }

    @PostMapping
    public Todo addNewTodo(@RequestBody  Todo newTodo) {
        return this.todoService.addNewTodo(newTodo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable String id , @RequestBody Todo todoPatch)
    {
        return this.todoService.updateTodo(id , todoPatch);
    }

    @DeleteMapping("/{id}")
    public Todo deleteTodo(@PathVariable String id)
    {
        return this.todoService.deleteTodo(id);
    }
}
