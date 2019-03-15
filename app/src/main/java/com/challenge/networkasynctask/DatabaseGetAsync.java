package com.challenge.networkasynctask;

import android.os.AsyncTask;

import com.challenge.networkasynctask.data.dao.TodoDao;
import com.challenge.networkasynctask.data.entity.Todo;

import java.util.List;

public class DatabaseGetAsync extends AsyncTask<Void, Void, List<Todo>> {

    private TodoDao todoDao;

    OnAsyncInteractionListener listener;

    public DatabaseGetAsync(TodoDao todoDao, OnAsyncInteractionListener listener) {
        this.todoDao = todoDao;
        this.listener = listener;
    }

    @Override
    protected List<Todo> doInBackground(Void... voids) {
        return todoDao.getTodoItems();
    }

    @Override
    protected void onPostExecute(List<Todo> todoList) {
        super.onPostExecute(todoList);
        listener.onPostExecuteDB(todoList);
    }
}
