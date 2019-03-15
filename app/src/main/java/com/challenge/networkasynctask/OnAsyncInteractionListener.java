package com.challenge.networkasynctask;

import com.challenge.networkasynctask.data.entity.Todo;

import java.util.List;

public interface OnAsyncInteractionListener {
    void onPreExecute();

    void onPostExecute(List<Todo> result);

    void onPostExecuteDB(List<Todo> result);
}
