package com.example.todo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Todo.class, Category.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
    public abstract CategoryDao categoryDao();
}
