package com.example.todo_list.controller;

import com.example.todo_list.entity.Todo;
import com.example.todo_list.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    // default test data
    Todo item1 = new Todo("61bb498bb77e9a58dc9e4920" , "item 1" , false);
    Todo item2 = new Todo("61bb498bb77e9a58dc9e4921" , "item 2" , false);

    @BeforeEach
    void setUp()
    {
        todoRepository.deleteAll();
        todoRepository.save(item1);
        todoRepository.save(item2);
    }

    @Test
    public void should_return_all_todo_when_getAllTodo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(item1.getId()))
                .andExpect(jsonPath("$[0].description").value("item 1"))
                .andExpect(jsonPath("$[0].done").value(false))
                .andExpect(jsonPath("$[1].id").value(item2.getId()))
                .andExpect(jsonPath("$[1].description").value("item 2"))
                .andExpect(jsonPath("$[1].done").value(false));
    }

    @Test
    public void should_return_deleted_todo_when_deleteTodo_given_todo_id() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/todos/{id}" , item1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(item1.getId()))
                .andExpect(jsonPath("$.description").value("item 1"))
                .andExpect(jsonPath("$.done").value(false));

        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(item2.getId()))
                .andExpect(jsonPath("$[0].description").value("item 2"))
                .andExpect(jsonPath("$[0].done").value(false));
    }

    @Test
    public void should_return_new_todo_when_newTodoItem_given_new_todo_item() throws Exception {
        // given
        String newItem = "{" +
                "\"description\": \"item 3\"," +
                "\"done\": false" +
                "}" ;

        // when

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newItem))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.description").value("item 3"))
                .andExpect(jsonPath("$.done").value(false));

    }

    @Test
    public void should_return_updated_todo_when_updateCompany_given_todo_patch() throws Exception {

        // given
        String newItem = "{" +
                "\"description\": \"item 2 new\"," +
                "\"done\": true" +
                "}" ;

        // when

        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/todos/{id}" , item2.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newItem))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(item2.getId()))
                .andExpect(jsonPath("$.description").value("item 2 new"))
                .andExpect(jsonPath("$.done").value(true));
    }


}