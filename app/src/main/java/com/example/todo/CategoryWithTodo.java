package com.example.todo;
import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;
public class CategoryWithTodo{
    @Embedded
    public Category category;
    @Relation(parentColumn = "id", entityColumn = "category_id")
    public List<Todo> todoList;
}
