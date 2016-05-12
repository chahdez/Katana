package app.katana.clases;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jchernandez on 06/05/2016.
 */
public abstract class LlamadaServidor extends AsyncTask<String,Void,Void> {
    private final HttpClient Client = new DefaultHttpClient();
    private final ResponseHandler<String> responseHandler = new BasicResponseHandler();
    private String Content = null;
    private String Error = null;
    private String WS;
    private String METHOD_CALL;
    private String NAME_SPACE = "http://services.aonaware.com";
    private String HOST = "http://services.aonaware.com";
    private String PATH_WS = "/webservices";
    private String PATH = "/DictService";
    private String SERVICE = "/DictService.asmx";
    private String METHOD_NAME;
    private String SOAP_ACTION;
    private String URL = HOST + PATH + SERVICE;

    private List<NameValuePair> parametros = new ArrayList<NameValuePair>();
    private Map<String,String> propiedades = new HashMap<String, String>();

    public abstract void Resultado(JSONArray Content) throws Exception;

    public abstract void Error(String Error);

    @Override
    protected Void doInBackground(String... params) {
        WS = params[0];
        switch (WS){
            case "REST" :
                URL = params[1];
                METHOD_CALL = params[2].toUpperCase();
                try {
                    switch (METHOD_CALL){
                        case  "GET" :
                            if(parametros.size() > 0){
                                String paramString = URLEncodedUtils.format(parametros, "utf-8");
                                URL += "?" + paramString;
                            }
                            HttpGet httpget = new HttpGet(URL);
                            Content = Client.execute(httpget, responseHandler);
                            break;
                        case "POST" :
                            HttpPost httpPost = new HttpPost(URL);
                            if(parametros.size() > 0){httpPost.setEntity(new UrlEncodedFormEntity(parametros));}
                            Content = "[" + Client.execute(httpPost,responseHandler).toString() + "]";
                            break;
                    }
                } catch (ClientProtocolException e) {
                    Error = e.getMessage();
                    cancel(true);
                } catch (IOException e) {
                    Error = e.getMessage();
                    cancel(true);
                }
                break;
            case "SOAP" :
                METHOD_NAME = params[1];
                SOAP_ACTION = HOST + PATH_WS +"/"+ METHOD_NAME;
                try{
                    SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
                    if(propiedades.size() > 0){
                        for (Map.Entry<String, String> entry : propiedades.entrySet()) {
                            request.addProperty(entry.getKey(), entry.getValue());
                        }
                    }
                    SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    sobre.dotNet = true;
                    sobre.setOutputSoapObject(request);
                    HttpTransportSE transporte = new HttpTransportSE(URL);
                    transporte.call(SOAP_ACTION, sobre);
                    SoapPrimitive respuesta =(SoapPrimitive)sobre.getResponse();
                    Content = respuesta.toString();
                } catch (Exception e) {
                    Error("Error");
                }
                break;
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
                Error = e.getMessage();
                Error(Error);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void parametro (String key , String value){
        parametros.add(new BasicNameValuePair(key , value));
    }

    public void propiedad (String key , String value){
        propiedades.put(key , value);
    }
}
