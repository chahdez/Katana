package app.katana.actividades.usuarios;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;

import app.katana.R;
import app.katana.clases.LlamadaServidor;
import app.katana.clases.Usuario;

/**
 * Created by jchernandez on 13/05/2016.
 */
public class UsuarioDetalleActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.usuario_detalle);

        String iduser = getIntent().getExtras().getString("iduser");

        String URL = "http://jsonplaceholder.typicode.com/users";
        LlamadaServidor peticion = new LlamadaServidor("REST") {
            @Override
            public void Resultado(JSONArray Content) throws Exception {
                Usuario usuario = new Usuario(Content.getJSONObject(0));

                TextView txtCompany = (TextView)findViewById(R.id.txtCompany);
                TextView txtName = (TextView) findViewById(R.id.txtName);
                TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
                TextView txtPhone = (TextView) findViewById(R.id.txtPhone);
                TextView txtWebSite = (TextView) findViewById(R.id.txtWebSite);

                txtName.setText(usuario.getName());
                txtCompany.setText(usuario.getCompany());
                txtEmail.setText(usuario.getEmail());
                txtPhone.setText(usuario.getPhone());
                txtWebSite.setText(usuario.getWebsite());
            }
            @Override
            public void Error(String Error) {
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
            }
        };
        peticion.parametro("id", iduser);
        peticion.execute("GET",URL);
    }
}
