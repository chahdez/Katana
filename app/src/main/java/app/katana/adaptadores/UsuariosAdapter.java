package app.katana.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import app.katana.R;
import app.katana.clases.Usuario;
import app.katana.view.UsuarioView;

import java.util.ArrayList;

/**
 * Created by jchernandez on 09/05/2016.
 */

public class UsuariosAdapter extends ArrayAdapter<Usuario> {
    private Context context;
    private int LayoutID;
    private ArrayList<Usuario> usuarios;

    public UsuariosAdapter(Context context, int resource, ArrayList<Usuario> usuarios) {
        super(context, resource, usuarios);
        this.context  = context;
        this.LayoutID = resource;
        this.usuarios = usuarios;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UsuarioView holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(LayoutID, parent, false);
            holder = new UsuarioView(context);
            holder.uid = (TextView)row.findViewById(R.id.txtID);
            holder.name = (TextView)row.findViewById(R.id.txtName);
            row.setTag(holder);
        }
        Usuario user = usuarios.get(position);

        holder.uid.setText(String.valueOf(user.getID()));
        holder.name.setText(String.valueOf(user.getName()));

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView uid = (TextView)v.findViewById(R.id.txtID);
                Toast.makeText(context,uid.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }

}
