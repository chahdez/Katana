package app.katana.clases;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jchernandez on 09/05/2016.
 */
public class Usuario implements Serializable {

    private int id;
    private String name;
    private String username;
    private String email;
    private JSONObject address;
    private String street;
    private String suit;
    private String city;
    private String zipcode;
    private JSONObject geo;
    private String lat;
    private String lng;
    private String phone;
    private String website;
    private JSONObject company;
    private String companyname;

    public Usuario(){}

    public Usuario(JSONObject json) throws Exception {
        this.id = json.getInt("id");
        this.name = json.getString("name");
        this.username = json.getString("username");
        this.email = json.getString("email");
        this.address = json.getJSONObject("address");
        this.street = address.getString("street");
        this.suit = address.getString("suite");
        this.city = address.getString("city");
        this.zipcode = address.getString("zipcode");
        this.geo = address.getJSONObject("geo");
        this.lat = geo.getString("lat");
        this.lng = geo.getString("lng");
        this.phone = json.getString("phone");
        this.website = json.getString("website");
        this.company = json.getJSONObject("company");
        this.companyname = company.getString("name");
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

    public int getID() {return id;}

    public String getName() {return name;}

    public String getUsername(){return username;}

    public String getEmail() { return email;}

    public String getStreet(){return street;}

    public String getSuit() {return suit;}

    public String getCity() {return city;}

    public String getZipcode() {return zipcode;}

    public String getLat() {return lat;}

    public String getLng() {return lng;}

    public String getPhone() {return phone;}

    public String getWebsite() {return website;}

    public String getCompany() { return companyname;}
}
