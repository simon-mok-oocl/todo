package com.example.todo_list.service;

import com.example.todo_list.entity.Todo;
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
//        Todo test = new Todo("1" , "test todo" , true);
//        List<Todo> result = new ArrayList<>();
//        result.add(test);

        return this.todoRepository.findAll();
    }


    public Todo addNewTodo(Todo newTodo) {
        return this.todoRepository.save(newTodo);
    }
}
