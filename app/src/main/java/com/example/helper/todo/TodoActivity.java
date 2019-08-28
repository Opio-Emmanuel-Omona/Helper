package com.example.helper.todo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.helper.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.example.helper.MainActivity.PREF_NAME;

public class TodoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TodoAdapter todoAdapter;
    ArrayList todos;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        initialiseTodos();
        initialiseRecyclerView();
    }

    private void initialiseTodos() {
        todos = new ArrayList();

        todos = getIntent().getParcelableArrayListExtra("todos");

        prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Set<String> todosSet = prefs.getStringSet("todos", Collections.singleton(""));
        Log.d("Todos", "" + todosSet);
//        todos = (ArrayList) todosSet;
    }

    private void initialiseRecyclerView() {
        recyclerView = findViewById(R.id.todo_recycler_view);
        layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        todoAdapter = new TodoAdapter(getApplicationContext(), (List<TodoModel>) todos);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(todoAdapter);

    }

}
