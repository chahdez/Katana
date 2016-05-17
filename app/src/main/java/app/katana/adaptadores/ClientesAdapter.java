package app.katana.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import app.katana.R;
import app.katana.actividades.clientes.ClienteDetalleActivity;
import app.katana.actividades.usuarios.UsuarioDetalleActivity;
import app.katana.clases.Cliente;
import app.katana.clases.Usuario;
import app.katana.view.UsuarioView;

/**
 * Created by Usuario on 16/05/16.
 */
public class ClientesAdapter extends ArrayAdapter<Cliente> {

    private Context context;
    private int LayoutID;
    private ArrayList<Cliente> clientes;

    public ClientesAdapter(Activity context, int resource, ArrayList<Cliente> clientes) {
        super(context, resource, clientes);
        this.context  = context;
        this.LayoutID = resource;
        this.clientes = clientes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout row = null;
        UsuarioView holder = null;
        final Cliente cli = clientes.get(position);
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = (LinearLayout) inflater.inflate(LayoutID, parent, false);
            holder = new UsuarioView(context);
            holder.name = (TextView)row.findViewById(R.id.txtName);
            row.setTag(holder);
        } else {
            row = (LinearLayout) convertView;
        }
        holder.name.setText(String.valueOf(cli.getNombre()));
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detalle = new Intent(context,ClienteDetalleActivity.class);
                detalle.putExtra("iduser", String.valueOf(cli.getId()));
                context.startActivity(detalle);
            }
        });
        return row;
    }
}
