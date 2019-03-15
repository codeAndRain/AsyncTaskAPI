package com.challenge.networkasynctask;

import android.os.AsyncTask;

import com.challenge.networkasynctask.data.dao.TodoDao;
import com.challenge.networkasynctask.data.entity.Todo;

import java.util.ArrayList;

public class DatabaseInsertAsync extends AsyncTask<ArrayList<Todo>, Void, Void> {

    TodoDao todoDao;

    public DatabaseInsertAsync(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @Override
    protected Void doInBackground(ArrayList<Todo>... arrayLists) {
        ArrayList<Todo> items = arrayLists[0];
        todoDao.insertAll(items);
        return null;
    }
}
