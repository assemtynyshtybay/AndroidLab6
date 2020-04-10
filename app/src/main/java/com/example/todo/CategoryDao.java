package com.example.todo;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import java.util.List;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM category_table")
    List<Category> getAllCategory();

    @Query("SELECT * FROM category_table WHERE id = :id")
    Category getCategoryById(int id);

    @Query("SELECT * FROM category_table WHERE id = :id")
    CategoryWithTodo getCategoryDetail(int id);

    @Insert
    void insert(Category category);
    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

}
