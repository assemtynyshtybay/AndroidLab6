package com.example.todo;
import android.app.Application;
import androidx.room.Room;

public class MyApplication extends Application {
    //MyApplication следить за всем, singleTon
    public static MyApplication instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        //инициализация
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "todo_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        //Эта наша таблица категории, откуда мы будем брать айдишку и тайтл категории для тудулиста
        try {
            database.categoryDao().insert(new Category("Sport"));
            database.categoryDao().insert(new Category("Learning"));
            database.categoryDao().insert(new Category("University"));
            database.categoryDao().insert(new Category("Work"));
            database.categoryDao().insert(new Category("Home"));
            database.categoryDao().insert(new Category("Another"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}