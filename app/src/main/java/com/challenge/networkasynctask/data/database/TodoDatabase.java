package com.challenge.networkasynctask.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.challenge.networkasynctask.data.dao.TodoDao;
import com.challenge.networkasynctask.data.entity.Todo;

@Database(entities = Todo.class, version = 1)
public abstract class TodoDatabase extends RoomDatabase {

    public static final String DB_NAME = "todo.db";

    private static TodoDatabase instance;

    public abstract TodoDao todoDao();

    public static TodoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, TodoDatabase.class, DB_NAME).build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }
}
