package com.challenge.networkasynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, HttpRequestAsync.OnAsyncInteractionListser {

    private ProgressBar progressBar;
    private Button requestButton;
    private TextView responseTextView;

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
        responseTextView = findViewById(R.id.response_text);

        requestButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try {
            String response = new HttpRequestAsync(this).execute(url).get();
            responseTextView.setText(response);
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
