package com.example.todo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DetailFragment  extends Fragment {

    public TextView id;
    public EditText title;
    public EditText description;
    public EditText status;
    public TextView category;
    public EditText duration;
    private CategoryDao categoryDao = MyApplication.getInstance().getDatabase().categoryDao();
    private TodoDao todoDao = MyApplication.getInstance().getDatabase().todoDao();

    public static DetailFragment newInstance(Todo todo) {
        DetailFragment fragment = new DetailFragment();
        //by use bundle give data to frag
        Bundle bundle = new Bundle();
        bundle.putString("id", todo.id + ""); //+" " чтобы он стал стрингом
        bundle.putString("title", todo.title);
        bundle.putString("description", todo.description);
        bundle.putString("status", todo.status);
        bundle.putString("duration", todo.duration);
        bundle.putInt("category", todo.categoryId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        final Button update = view.findViewById(R.id.update);
        //click update
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int todo_id = Integer.parseInt(id.getText().toString());
                Todo todo = todoDao.getTodoById(todo_id);
                todo.title = title.getText().toString();
                todo.status = status.getText().toString();
                todo.description = description.getText().toString();
                todo.duration = duration.getText().toString();
                todoDao.update(todo);
                //open feag with new data
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment, MainFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        final Button delete = view.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int todo_id = Integer.parseInt(id.getText().toString());
                Todo todo = todoDao.getTodoById(todo_id);
                todoDao.delete(todo);
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment, MainFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = view.findViewById(R.id.detail_id);
        title = view.findViewById(R.id.detail_title);
        description = view.findViewById(R.id.detail_description);
        status = view.findViewById(R.id.detail_status);
        category = view.findViewById(R.id.detail_category);
        duration = view.findViewById(R.id.detail_duration);
        try {

            //getArguments() from bundle
            id.setText(getArguments().getString("id"));
            title.setText(getArguments().getString("title"));
            description.setText(getArguments().getString("description"));
            status.setText(getArguments().getString("status"));
            duration.setText(getArguments().getString("duration"));
            int category_id = getArguments().getInt("category");
            category.setText(categoryDao.getCategoryById(category_id).title);
        }
        catch (Exception e) {
            Log.e("Error", e + " ");
        }

    }
}