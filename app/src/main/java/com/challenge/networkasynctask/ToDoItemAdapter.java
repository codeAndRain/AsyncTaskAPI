package com.challenge.networkasynctask;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ToDoItemAdapter extends RecyclerView.Adapter<ToDoViewHolder> {

    List<Todo> todoList = new ArrayList<>();

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder toDoViewHolder, int position) {
        Todo todo = todoList.get(position);
        toDoViewHolder.bind(todo);
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public void setItems(List<Todo> items) {
        if (!todoList.isEmpty()) {
            todoList.clear();
        }
        todoList.addAll(items);
        notifyDataSetChanged();
    }
}
