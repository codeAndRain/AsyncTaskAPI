package com.challenge.networkasynctask;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.challenge.networkasynctask.data.entity.Todo;

class ToDoViewHolder extends RecyclerView.ViewHolder {

    TextView todoText;

    public ToDoViewHolder(@NonNull View itemView) {
        super(itemView);
        todoText = itemView.findViewById(R.id.todo_text_view);
    }

    public void bind(Todo todo) {
        todoText.setText(todo.getTitle());
    }
}
