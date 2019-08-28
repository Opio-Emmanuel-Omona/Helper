package com.example.helper.todo

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

import com.example.helper.R


import com.example.helper.MainActivity.PREF_NAME

class TodoActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var todoAdapter: TodoAdapter
    private lateinit var todos: List<String>
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)

        initialiseTodos()
        initialiseRecyclerView()
    }

    private fun initialiseTodos() {

        todos = intent.getParcelableArrayListExtra("todos")

        prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val todosSet = prefs.getStringSet("todos", setOf(""))
        Log.d("Todos", "" + todosSet!!)
        //        todos = (ArrayList) todosSet;
    }

    private fun initialiseRecyclerView() {
        recyclerView = findViewById(R.id.todo_recycler_view)
        layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        todoAdapter = TodoAdapter(applicationContext, todos as List<TodoModel>)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = todoAdapter

    }

}
