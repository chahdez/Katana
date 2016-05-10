package app.katana.clases;

import android.os.AsyncTask;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by jchernandez on 06/05/2016.
 */
public abstract class LlamadaServidor extends AsyncTask<String,Void,Void> {
    private final HttpClient Client = new DefaultHttpClient();
    private String Content = null;
    private String Error = null;


    public abstract void Resultado(JSONArray Content) throws Exception;

    public abstract void Error(String Error);

    @Override
    protected Void doInBackground(String... params) {
        try {
            HttpGet httpget = new HttpGet(params[0]);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            Content = Client.execute(httpget, responseHandler);
        } catch (ClientProtocolException e) {
            Error = e.getMessage();
            cancel(true);
        } catch (IOException e) {
            Error = e.getMessage();
            cancel(true);
        }
        return null;
    }

    protected void onPostExecute(Void unused) {
        if(Error != null){
            Error(Error);
        } else {
            try {
                JSONArray array = new JSONArray(Content);
                Resultado(array);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
