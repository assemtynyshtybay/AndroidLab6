package com.example.todo;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "todo_table",
        foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id", onDelete = CASCADE),
        indices = {@Index(value ="category_id")})

public class Todo {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    public String description;

    @ColumnInfo(name = "status")
    public String status;

    @ColumnInfo(name="category_id")
    public int categoryId;

    public String duration;

    public Todo() {
        this.status = "Not done";
    }

    public Todo(int categoryId) {
        this.status = "Not done";
        this.categoryId = categoryId;
    }

}
