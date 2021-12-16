package com.example.todo_list.service;

import com.example.todo_list.entity.Todo;
import com.example.todo_list.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class TodoServiceTest {
    @Mock
    TodoRepository todoRepository;

    @InjectMocks
    TodoService todoService;


    @Test
    public void should_return_todo_list_when_getTodoList() {
        // given
        Todo item1 = new Todo("1", "item 1" , false);
        Todo item2 = new Todo("2", "item 2" , false);
        List<Todo> todos = new ArrayList<>();
        todos.add(item1);
        todos.add(item2);
        given(todoRepository.findAll()).willReturn(todos);

        // when
        List<Todo> actual = todoService.findAll();

        // then
        assertEquals(todos, actual);

    }

    @Test
    public void should_return_deleted_todo_when_delete_todo_given_todo_id()
    {
        // given
        Todo item = new Todo("1", "item 1" , false);
        given(todoRepository.findById(any())).willReturn(Optional.of(item));

        // when
        Todo actual = todoService.deleteTodo("1");

        // then
        verify(todoRepository).delete(item);
        assertEquals(item , actual);

    }

    @Test
    public void should_return_new_todo_when_addTodoItem_given_new_todo()
    {
        // given
        Todo item = new Todo("1", "item 1" , false);
        given(todoRepository.save(any())).willReturn(item);

        // when
        Todo actual = todoService.addNewTodo(item);

        // then
        assertEquals(item , actual);
    }

    @Test
    public void should_return_patched_todo_when_update_todo_given_todo_patch()
    {
        // given
        Todo item1 = new Todo("1", "item 1" , false);
        Todo patch = new Todo( "1", "item 1 edit" , true);
        given(todoRepository.findById(any())).willReturn(Optional.of(item1));
        given(todoRepository.save(any())).willReturn(patch);

        // when
        Todo actual = todoService.updateTodo("1" , patch);

        // then
        assertEquals(patch.getDescription() , actual.getDescription());
        assertEquals(patch.getDone() , actual.getDone());


    }


}