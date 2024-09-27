package com.example.spring_boot_34.__todo_app.repositories;

import com.example.spring_boot_34.__todo_app.models.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
}
