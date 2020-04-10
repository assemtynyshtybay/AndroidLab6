package com.example.todo;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
public class AddTodoFragment extends Fragment {
    AppDatabase db;
    TodoDao todoDao;
    CategoryDao categoryDao;

    //чтобы каждый раз не писать AddTodoFragment addTodoFragment= new AddTodoFragment();
    public static AddTodoFragment newInstance() {
        return new AddTodoFragment();
    }

    //при создании вюшки выполняется эта логика
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container,false);
        db=MyApplication.getInstance().getDatabase();
        todoDao=db.todoDao();
        categoryDao=db.categoryDao();
        // Предоставляем ссылку куда должна расположиться
        final EditText insertTitle = view.findViewById(R.id.insert_title);
        final EditText insertDescription = view.findViewById(R.id.insert_description);
        final EditText insertCategory = view.findViewById(R.id.insert_category);
        final EditText insertDuration = view.findViewById(R.id.insert_duration);
        //при нажатии на кнопку add
        Button button= view.findViewById(R.id.insert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //пробуем
                try {
                    //получаем данные с вюшек
                    String titleStr=insertTitle.getText().toString();
                    String descriptionStr=insertDescription.getText().toString();
                    int category_id=Integer.parseInt(insertCategory.getText().toString()); //так как мы ожидаем интержер
                    String durationStr=insertDuration.getText().toString();
                    //кладем в таблицу туду
                    Todo todo = new Todo();
                    todo.title=titleStr;
                    todo.description=descriptionStr;
                    todo.duration=durationStr;
                    //получаем по айдишке категории с таблицы категория и кладем ее тоже в таблицу туду
                    categoryDao.getCategoryById(category_id);
                    todo.categoryId = category_id;
                    todoDao.insert(todo);
                    //тут мы реплэйсаем фрагменты, то есть открываем главный фрагмент с обновленными данными
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment, MainFragment.newInstance())
                                .addToBackStack(null)
                                .commit();
                }
                //иначе вводим в лог ошибку
                catch (Exception e) {
                    Log.e("Error: ", e.toString());
                }
            }
        });
        return view;
    }
    //когда вюшка будет создана
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = MyApplication.getInstance().getDatabase();
        todoDao = db.todoDao();
        categoryDao = db.categoryDao();
    }


}