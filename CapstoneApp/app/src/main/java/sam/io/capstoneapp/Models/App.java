package sam.io.capstoneapp.Models;

public class App {
    String name;
    String category;
    String address;
    String hour;
    String phone;
    String distance;
    String Cont_Affiliation;
    String Cont_Services;
    String Cont_Hmo;
    String Cont_Offers;
    double latitude;
    double longitude;

    public App(String name,String category, String address, String hour, String phone, double latitude, double longitude,String Cont_Affiliation,String Cont_Services,String Cont_Hmo,String Cont_Offers) {
        this.name = name;
        this.category = category;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hour = hour;
        this.phone = phone;
        this.Cont_Affiliation = Cont_Affiliation;
        this.Cont_Services = Cont_Services;
        this.Cont_Hmo = Cont_Hmo;
        this.Cont_Offers = Cont_Offers;
    }

    public App() {

    }

    public String getName() {
        return name;
    }

    public String getAddress() { return address; }


    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCont_Services() {
        return Cont_Services;
    }

    public void setCont_Services(String cont_Services) {
        Cont_Services = cont_Services;
    }

    public String getCont_Hmo() {
        return Cont_Hmo;
    }

    public void setCont_Hmo(String cont_Hmo) {
        Cont_Hmo = cont_Hmo;
    }

    public String getCont_Offers() {
        return Cont_Offers;
    }

    public void setCont_Offers(String cont_Offers) {
        Cont_Offers = cont_Offers;
    }

    public String getCont_Affiliation() { return Cont_Affiliation; }

    public void setCont_Affiliation(String cont_Affiliation) { Cont_Affiliation = cont_Affiliation; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }
}
