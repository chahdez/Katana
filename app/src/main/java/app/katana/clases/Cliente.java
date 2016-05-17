package app.katana.clases;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jchernandez on 09/05/2016.
 */
public class Cliente {

    private int id;
    private String nombre;
    private String rfc;
    private String calle;
    private String numInterior;
    private String numExterior;
    private String colonia;
    private String municipio;
    private String cp;
    private String tel;
    private String correo;
    private double saldo;

    public Cliente(){}

    public Cliente(JSONObject json) throws Exception {
        this.id = json.getInt("Clave");
        this.nombre = json.getString("Nombre");
        this.rfc = json.getString("RFC");
        this.calle = json.getString("Calle");
        this.numInterior = json.getString("NumInt");
        this.numExterior = json.getString("NumExt");
        this.colonia = json.getString("Colonia");
        this.municipio = json.getString("Municipio");
        this.cp = json.getString("CP");
        this.tel = json.getString("Telefono");
        this.correo = json.getString("Correo");
        this.saldo = json.getDouble("Saldo");
    }

    public static ArrayList<Cliente> getLista(JSONArray array){
        ArrayList<Cliente> resultado = new ArrayList<Cliente>();
        try {
            for (int i = 0; i < array.length(); i++) {
                Cliente cli = new Cliente(array.getJSONObject(i));
                resultado.add(cli);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return resultado;
    }

    public int getId() {
        return id;
    }

    public String getRfc() {
        return rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumInterior() {
        return numInterior;
    }

    public String getCalle() {
        return calle;
    }

    public String getColonia() {
        return colonia;
    }

    public String getNumExterior() {
        return numExterior;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getTel() {
        return tel;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCp() {
        return cp;
    }

    public double getSaldo() {
        return saldo;
    }
}

