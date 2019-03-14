package com.challenge.networkasynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, HttpRequestAsync.OnAsyncInteractionListser {

    private ProgressBar progressBar;
    private Button requestButton;
    private RecyclerView recyclerView;

    private ToDoItemAdapter adapter;


    public String url = "https://jsonplaceholder.typicode.com/todos/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        progressBar = findViewById(R.id.progress_circular);
        requestButton = findViewById(R.id.request_button);
        recyclerView = findViewById(R.id.recycler_view);

        adapter = new ToDoItemAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        requestButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try {
            List<Todo> response = new HttpRequestAsync(this).execute(url).get();
            adapter.setItems(response);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPostExecute() {
        progressBar.setVisibility(View.GONE);
    }

}
