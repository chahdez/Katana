package app.katana.actividades.clientes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;

import app.katana.R;
import app.katana.adaptadores.ClientesAdapter;
import app.katana.clases.Cliente;
import app.katana.clases.LlamadaServidor;

/**
 * Created by Usuario on 16/05/16.
 */
public class ClientesActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String URL = "http://jsonplaceholder.typicode.com/users";
        LlamadaServidor peticion = new LlamadaServidor("SOAP") {
            @Override
            public void Resultado(JSONArray Content) throws Exception {
                ArrayList<Cliente> list = new Cliente().getLista(Content);
                ListView lv = (ListView)findViewById(R.id.lv);
                ClientesAdapter adapter = new ClientesAdapter(ClientesActivity.this,R.layout.list_item,list);
                lv.setAdapter(adapter);
                Log.d("Content", Content.toString());
            }
            @Override
            public void Error(String Error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        };
        //peticion.execute("GET",URL);
        peticion.execute("AndroidClientes");
    }
}
