package app.katana.actividades.usuarios;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import java.util.ArrayList;
import app.katana.R;
import app.katana.adaptadores.UsuariosAdapter;
import app.katana.clases.LlamadaServidor;
import app.katana.clases.Usuario;

/**
 * Created by jchernandez on 13/05/2016.
 */
public class UsuariosActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
            String URL = "http://jsonplaceholder.typicode.com/users";
            LlamadaServidor peticion = new LlamadaServidor("SOAP") {
                @Override
                public void Resultado(JSONArray Content) throws Exception {
                    /*ArrayList<Usuario> list = new Usuario().getLista(Content);
                    ListView lv = (ListView)findViewById(R.id.lv);
                    UsuariosAdapter adapter = new UsuariosAdapter(UsuariosActivity.this,R.layout.list_item,list);
                    lv.setAdapter(adapter);*/
                    Log.d("Content" , Content.toString());
                }
                @Override
                public void Error(String Error) {
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                }
            };
            //peticion.execute("GET",URL);
            peticion.execute("AndroidClientes");
        }
}
