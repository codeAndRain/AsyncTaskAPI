package com.challenge.networkasynctask;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestAsync extends AsyncTask<String, Void, String> {

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 10000;
    public static final int CONNECTION_TIMEOUT = 10000;

    interface OnAsyncInteractionListser {
        void onPreExecute();

        void onPostExecute();
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
    protected String doInBackground(String... strings) {
        String remoteUrl = strings[0];
        String result;
        String inputLine;

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

        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        listener.onPostExecute();
    }
}
