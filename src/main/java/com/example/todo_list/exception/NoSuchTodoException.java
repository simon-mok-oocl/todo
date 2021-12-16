package com.example.todo_list.exception;

public class NoSuchTodoException extends RuntimeException{
    public NoSuchTodoException()
    {
        super("No such Todo item");
    }

}
