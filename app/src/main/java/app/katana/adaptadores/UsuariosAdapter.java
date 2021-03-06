package app.katana.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

import app.katana.R;
import app.katana.actividades.usuarios.UsuarioDetalleActivity;
import app.katana.clases.Usuario;
import app.katana.view.UsuarioView;

/**
 * Created by jchernandez on 09/05/2016.
 */
public class UsuariosAdapter extends ArrayAdapter<Usuario> {
    private Context context;
    private int LayoutID;
    private ArrayList<Usuario> usuarios;

    public UsuariosAdapter(Activity context, int resource, ArrayList<Usuario> usuarios) {
        super(context, resource, usuarios);
        this.context  = context;
        this.LayoutID = resource;
        this.usuarios = usuarios;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout row = null;
        UsuarioView holder = null;
        final Usuario user = usuarios.get(position);
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = (LinearLayout) inflater.inflate(LayoutID, parent, false);
            holder = new UsuarioView(context);
            holder.name = (TextView)row.findViewById(R.id.txtName);
            row.setTag(holder);
        } else {
            row = (LinearLayout) convertView;
        }
        holder.name.setText(String.valueOf(user.getName()));
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detalle = new Intent(context,UsuarioDetalleActivity.class);
                detalle.putExtra("iduser", String.valueOf(user.getID()));
                context.startActivity(detalle);
            }
        });
        return row;
    }
}
