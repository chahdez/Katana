package app.katana.actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.katana.R;
import app.katana.actividades.clientes.ClientesActivity;

/**
 * Created by jchernandez on 13/05/2016.
 */
public class Login extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Button login = (Button)findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio =   new Intent(Login.this, ClientesActivity.class);
                startActivity(inicio);
            }
        });
    }
}
