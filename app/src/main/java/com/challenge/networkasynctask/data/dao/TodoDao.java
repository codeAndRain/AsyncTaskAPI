package com.challenge.networkasynctask.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.challenge.networkasynctask.data.entity.Todo;

import java.util.List;

@Dao
public interface TodoDao {

    @Query("SELECT * FROM todo_table")
    List<Todo> getTodoItems();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Todo> todoList);
}
