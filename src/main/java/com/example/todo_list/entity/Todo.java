package com.example.todo_list.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("todo")
public class Todo {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String description;

    //@JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean done;

    public Todo(String id, String description, Boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean getDone() {
        return done;
    }
}
