package com.example.spring_boot_34.__todo_app.services;

import com.example.spring_boot_34.__todo_app.models.TodoItem;
import com.example.spring_boot_34.__todo_app.repositories.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

/**
 * Service class for managing TodoItem entities. This class provides methods
 * to perform CRUD operations such as retrieving, saving, and deleting
 * todo items from the database.
 */
@Service
public class TodoItemService {

    // Injects the TodoItemRepository for performing CRUD operations
    @Autowired
    private TodoItemRepository todoItemRepository;

    /**
     * Retrieves a TodoItem by its id.
     * return an optional containing the TodoItem if found, or empty if not found.
     */
    public Optional<TodoItem> getById(Long id) { return todoItemRepository.findById(id); }

    /**
     * Retrieves all TodoItems from the repository.
     * return an iterable containing all TodoItems.
     */
    public Iterable<TodoItem> getAll() {
        return todoItemRepository.findAll();
    }

    /**
     * Saves or updates a TodoItem in the repository. If the TodoItem is new,
     * it sets the creation timestamp. Otherwise, it updates the modification timestamp.
     * return the saved TodoItem instance from the repository.
     */
    public TodoItem save(TodoItem todoItem) {
        if (todoItem.getId() == null) { todoItem.setCreatedAt(Instant.now()); }
        todoItem.setUpdatedAt(Instant.now());
        return  todoItemRepository.save(todoItem);
    }

    public void delete(TodoItem todoItem) {
        todoItemRepository.delete(todoItem);
    }
}
