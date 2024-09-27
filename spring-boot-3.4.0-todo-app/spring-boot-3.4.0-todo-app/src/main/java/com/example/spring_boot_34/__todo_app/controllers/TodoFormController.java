package com.example.spring_boot_34.__todo_app.controllers;

import com.example.spring_boot_34.__todo_app.models.TodoItem;
import com.example.spring_boot_34.__todo_app.services.TodoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * This controller handles HTTP requests related to creating, updating, and deleting
 * TodoItem entities. It manages the form submission and validation process for Todo items.
 */

@Controller
public class TodoFormController {

    // Injects the TodoItemService to interact with the TodoItem repository.
    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/create-todo")
    public String showCreatedForm(TodoItem todoItem) {
        return "new-todo-item";
    }

    /**
     * Handles POST requests to "/todo" to create a new TodoItem. Validates the form data and,
     * if valid, saves the new TodoItem to the database.
     * return A redirection to the home page if successful,
     * or the form view with an error statement if there are validation errors.
     */
    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model){

        if (result.hasErrors()) { return "new-todo-item";}

        TodoItem item = new TodoItem();
        item.setDescription(todoItem.getDescription());
        item.setIsComplete(todoItem.getIsComplete());

        todoItemService.save(item);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
        TodoItem todoItem = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        todoItemService.delete(todoItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdatedForm(@PathVariable("id") Long id, Model model) {

        TodoItem todoItem = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        model.addAttribute("todo", todoItem);
        return "edit-todo-item";
    }

    /**
     * Handles POST requests to "/todo/{id}" to update an existing TodoItem.
     * Validates the form data and, if valid, updates the TodoItem in the database.
     * return a redirection to the home page if successful.
     */
    //TODO if invalid, return a to the form page with an error message instead of the home page 
    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid TodoItem todoItem, BindingResult bindingResult, Model model){


        // Check for validation errors
        if (bindingResult.hasErrors()) { return "redirect:/"; }

        TodoItem item = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        item.setIsComplete(todoItem.getIsComplete());
        item.setDescription(todoItem.getDescription());

        todoItemService.save(item);

        return "redirect:/";
    }
}
