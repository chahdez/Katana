package app.katana.actividades;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import app.katana.R;

public class MyActivity extends Activity {
    private ProgressDialog progressDialog;
    private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if(isOnline()){
            Intent init = new Intent(MyActivity.this, Login.class);
            startActivity(init);
        }
    }

    public void initActionBar(){
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

    public void hideActionBar() {actionBar.hide();}

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
