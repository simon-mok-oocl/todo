package com.example.todo_list.service;

import com.example.todo_list.entity.Todo;
import com.example.todo_list.exception.NoSuchTodoException;
import com.example.todo_list.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll()
    {
        return this.todoRepository.findAll();
    }


    public Todo addNewTodo(Todo newTodo) {
        return this.todoRepository.save(newTodo);
    }

    public Todo updateTodo(String id, Todo todoPatch) {
        Todo useTodo = this.todoRepository.findById(id).orElseThrow(NoSuchTodoException::new);

        if(todoPatch.getDescription() != null)
        {
            useTodo.setDescription(todoPatch.getDescription());
        }

        if(todoPatch.getDone() != null)
        {
            useTodo.setDone(todoPatch.getDone());
        }
        return this.todoRepository.save(useTodo);
    }

    public Todo deleteTodo(String id) {
        Todo rip = this.todoRepository.findById(id).orElseThrow(NoSuchTodoException::new);
        this.todoRepository.delete(rip);
        return rip;
    }
}
