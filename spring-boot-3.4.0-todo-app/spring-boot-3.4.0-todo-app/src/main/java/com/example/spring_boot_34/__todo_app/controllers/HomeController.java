package com.example.spring_boot_34.__todo_app.controllers;

import com.example.spring_boot_34.__todo_app.services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * HomeController is responsible for handling the root ("/") endpoint
 * and returning the view for the home page. It uses the {@link TodoItemService}
 * to retrieve all TodoItems and display them on the home page.
 */
@Controller
public class HomeController {

    // Injects the TodoItemService to interact with TodoItem data
    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", todoItemService.getAll());
        return modelAndView;
    }

}
