package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainFragment fragmentList=new MainFragment();
        //добавляем наш MainFragment
        FragmentManager fragmentManager=getFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.fragment, MainFragment.newInstance())
                .commit();

        button=findViewById(R.id.add_button);
        //open AddTodoFragment
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTodoFragment addTodoFragment=new AddTodoFragment();
                FragmentManager manager = getFragmentManager();
                manager
                        .beginTransaction()
                        .replace(R.id.fragment, addTodoFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

}
