package app.katana;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import app.katana.adaptadores.UsuariosAdapter;
import app.katana.clases.LlamadaServidor;
import app.katana.clases.Usuario;
import org.json.JSONArray;

import java.util.ArrayList;

public class MyActivity extends Activity {
    private ProgressDialog progressDialog;
    private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //inicializarActionBar();
        if(isOnline()){
            String URL = "http://jsonplaceholder.typicode.com/users";
            cargando(this, true);
            LlamadaServidor peticion = new LlamadaServidor("REST") {
                @Override
                public void Resultado(JSONArray Content) throws Exception {
                    ListView lv;
                    ArrayList<Usuario> list;
                    Usuario usr = new Usuario();
                    UsuariosAdapter adapter;

                    list = usr.getLista(Content);
                    lv = (ListView)findViewById(R.id.lv);
                    adapter = new UsuariosAdapter(MyActivity.this,R.layout.list_item,list);
                    lv.setAdapter(adapter);
                    cargando(MyActivity.this,true);
                    Log.d("Content" , Content.toString());
                }
                @Override
                public void Error(String Error) {
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                }
            };
            /*SOAP
            peticion.propiedad("x", "1");
            peticion.propiedad("y", "1");
            peticion.execute("SOAP", "ServerInfo");*/
            /*REST*/
            peticion.parametro("userid","1");
            peticion.execute("REST",URL,"GET");
        }
    }

    private void inicializarActionBar(){
        LayoutInflater inflator = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        View v = inflator.inflate(R.layout.menu, null);
        actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setCustomView(v, lp);
    }

    public boolean isOnline(){
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean status = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            status = true;
        }
        return status;
    }

    protected void cargando(Context context, boolean visible) {
        if(visible && (progressDialog == null || !progressDialog.isShowing())){
            progressDialog = ProgressDialog.show(context, "", "Cargando...", true, false);
        } else if (progressDialog != null){
            progressDialog.dismiss();
        }
    }
}
