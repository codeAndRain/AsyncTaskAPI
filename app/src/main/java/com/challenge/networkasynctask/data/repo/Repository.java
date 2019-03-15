package com.challenge.networkasynctask.data.repo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.challenge.networkasynctask.DatabaseGetAsync;
import com.challenge.networkasynctask.DatabaseInsertAsync;
import com.challenge.networkasynctask.HttpRequestAsync;
import com.challenge.networkasynctask.OnAsyncInteractionListener;
import com.challenge.networkasynctask.data.dao.TodoDao;
import com.challenge.networkasynctask.data.database.TodoDatabase;
import com.challenge.networkasynctask.data.entity.Todo;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private String url = "https://jsonplaceholder.typicode.com/todos/";

    private TodoDao todoDao;
    private Context context;
    private OnAsyncInteractionListener listener;


    public Repository(Context context, OnAsyncInteractionListener listener) {
        TodoDatabase database = TodoDatabase.getInstance(context);
        todoDao = database.todoDao();
        this.listener = listener;
        this.context = context;
    }

    public void getTodoList() {
        if (isNetworkAvailable()) {
            new HttpRequestAsync(listener).execute(url);
        } else {
            new DatabaseGetAsync(todoDao, listener).execute();
        }
    }

    public void insert(List<Todo> todoItems) {
        ArrayList todoList = new ArrayList<>(todoItems);
        new DatabaseInsertAsync(todoDao).execute(todoList);
    }

    public void destroyDatabaseInstance() {
        TodoDatabase.destroyInstance();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
}
