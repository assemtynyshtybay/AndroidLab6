package com.example.todo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table", indices = {@Index(value = {"title"}, unique = true)})
public class Category {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;

    public Category(String title) {
        this.title = title;
    }
}
