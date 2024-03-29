package com.example.helper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helper.alarm.TimerActivity;
import com.example.helper.todo.TodoActivity;
import com.example.helper.todo.TodoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    TextView textView;
    ImageButton voiceButton;
    private ArrayList todos;
    private Set<String> todosSet;
    SharedPreferences.Editor editor;
    public static final String PREF_NAME = "TODOS_PREF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        voiceButton = findViewById(R.id.voiceButton);
        todos = new ArrayList();
        todosSet = new ArraySet<>();

        editor = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();

        voiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }

    private void speak() {
        // Intent to show speech dialog
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something");

        // Start intent
        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_SPEECH_INPUT:
                if (resultCode == RESULT_OK && null != data) {
                    // Get text array from voice intent
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    // Set to textView
                    String spokenText = result.get(0);
                    textView.setText(spokenText);
                    todos.add(new TodoModel(spokenText, false, ""));
                    saveToPref(spokenText);
                }
        }
    }

    private void saveToPref(String spokenText) {
        todosSet.add(spokenText);
        Log.d("adding todos", "" + todosSet);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.item1:
                return true;

            case R.id.item2:
                 intent = new Intent(this, Text2Speak.class);
                startActivity(intent);
                return true;

            case R.id.item3:
                intent = new Intent(this, TimerActivity.class);
                startActivity(intent);
                return true;

            case R.id.item4:
                intent = new Intent(this, TodoActivity.class);
                Bundle bundle = new Bundle();
                editor.putStringSet("todos", todosSet);
                editor.apply();
                bundle.putParcelableArrayList("todos", (ArrayList<? extends Parcelable>) todos);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
