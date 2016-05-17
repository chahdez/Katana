package app.katana.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import org.w3c.dom.Text;

/**
 * Created by jchernandez on 09/05/2016.
 */
public class UsuarioView extends View {

    public TextView uid;
    public TextView name;
    public TextView username;
    public TextView email;

    public UsuarioView(Context context) {
        super(context);
    }
}
