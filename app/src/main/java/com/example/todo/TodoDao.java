package com.example.todo;

import android.widget.LinearLayout;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {
    @Query("SELECT * FROM todo_table")
    List<Todo> getAllTodo();

    @Query("SELECT * FROM todo_table WHERE id = :id")
    Todo getTodoById(int id);

    @Insert
    void insert(Todo todo);

    @Update
    void update(Todo todo);

    @Delete
    void delete(Todo todo);


    @Delete
    void deleteAll(List<Todo> todoList);
}
