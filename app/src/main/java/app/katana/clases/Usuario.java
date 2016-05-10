package app.katana.clases;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by jchernandez on 09/05/2016.
 */
public class Usuario {


    private int id;
    private String username;
    private String email;
    private JSONArray address;
    private String street;
    private String suit;
    private String city;
    private String zipcode;
    private JSONArray geo;
    private String lat;
    private String lng;
    private String phone;
    private String website;
    private JSONArray company;
    private String name;
    private String catchPhrase;
    private String bs;


    public Usuario(){}

    public Usuario(JSONObject json) throws Exception {
        this.id = json.getInt("id");
        this.username = json.getString("name");
    }

    public static ArrayList<Usuario> getLista(JSONArray array){
        ArrayList<Usuario> resultado = new ArrayList<Usuario>();

        try {
            for (int i = 0; i < array.length(); i++) {
                Usuario usr = new Usuario(array.getJSONObject(i));
                resultado.add(usr);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return resultado;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.username;
    }
}
