package app.katana.actividades.clientes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;

import app.katana.R;
import app.katana.clases.LlamadaServidor;

/**
 * Created by Usuario on 16/05/16.
 */
public class ClienteDetalleActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cliente_detalle);

        String iduser = getIntent().getExtras().getString("iduser");

        LlamadaServidor peticion = new LlamadaServidor("SOAP") {
            @Override
            public void Resultado(JSONArray Content) throws Exception {
                Log.d("CLIENTE", String.valueOf(Content));
            }
            @Override
            public void Error(String Error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        };
        peticion.parametro("Clave", iduser);
        peticion.execute("AndoridClientes");
    }
}
