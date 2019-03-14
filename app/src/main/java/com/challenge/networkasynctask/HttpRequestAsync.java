package com.challenge.networkasynctask;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HttpRequestAsync extends AsyncTask<String, Void, List<Todo>> {

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 10000;
    public static final int CONNECTION_TIMEOUT = 10000;

    interface OnAsyncInteractionListser {
        void onPreExecute();

        void onPostExecute(List<Todo> result);
    }

    private OnAsyncInteractionListser listener;

    public HttpRequestAsync(OnAsyncInteractionListser listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onPreExecute();
    }

    @Override
    protected List<Todo> doInBackground(String... strings) {
        String remoteUrl = strings[0];
        String result;
        String inputLine;
        List<Todo> todoList = new ArrayList<>();

        try {
            // Create a URL object
            URL url = new URL(remoteUrl);

            // Create a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set Methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            // Connect to the url
            connection.connect();

            // Create InputStream
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());

            // Create a Buffered Reader and a String Builder
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            // Check if the line we are reading is not null
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            // close the inputStream
            inputStreamReader.close();
            bufferedReader.close();

            // set the result
            result = stringBuilder.toString();

            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                Todo todoItem = convertTodo(jsonArray.getJSONObject(i));
                todoList.add(todoItem);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return todoList;
    }

    private Todo convertTodo(JSONObject jsonObject) throws JSONException {
        String title = jsonObject.getString("title");
        return new Todo(title);
    }

    @Override
    protected void onPostExecute(List<Todo> result) {
        super.onPostExecute(result);
        listener.onPostExecute(result);
    }
}
