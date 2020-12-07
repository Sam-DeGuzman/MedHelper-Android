package sam.io.capstoneapp.Models;

import android.os.Parcel;
import android.os.Parcelable;
/*
Contact_Name
Contact_Number
Contact_Image
Category_Image
Contact_Address
Category_Name
Services
HMO
Offers
Service_Schedule
 */
public class CallDirectoryItems {
    String Cont_Name;
    String Cont_Number;
    String Cont_Image;
    String Cat_Image;
    String Cont_Address;
    String Cat_Name;
    String Cont_Affiliation;
    String Cont_Services;
    String Cont_Hmo;
    String Cont_Offers;
    String Cont_Service_Sched;

    public CallDirectoryItems(String cont_Name, String cont_Number,
                              String cont_Image, String cat_Image,
                              String cont_Address,String cat_Name,
                              String cont_Affiliation,
                              String cont_Services,String cont_Hmo,
                              String cont_Offers, String cont_Service_Sched) {
        this.Cont_Name = cont_Name;
        this.Cont_Number = cont_Number;
        this.Cont_Image = cont_Image;
        this.Cat_Image = cat_Image;
        this.Cont_Address = cont_Address;
        this.Cat_Name = cat_Name;
        this.Cont_Affiliation = cont_Affiliation;
        this.Cont_Services = cont_Services;
        this.Cont_Hmo = cont_Hmo;
        this.Cont_Offers = cont_Offers;
        this.Cont_Service_Sched = cont_Service_Sched;
    }

    public CallDirectoryItems() {

    }




    public String getCont_Name() {
        return Cont_Name;
    }

    public String getCont_Number() {
        return Cont_Number;
    }

    public String getCont_Image() {
        return Cont_Image;
    }

    public String getCat_Image() {
        return Cat_Image;
    }

    public String getCont_Address() { return Cont_Address; }

    public String getCat_Name() { return Cat_Name; }

    public String getCont_Affiliation() { return Cont_Affiliation; }

    public String getCont_Services() { return Cont_Services; }

    public String getCont_Hmo() { return Cont_Hmo; }

    public String getCont_Offers() { return Cont_Offers; }

    public String getCont_Service_Sched() { return Cont_Service_Sched; }

    public void setCont_Name(String cont_Name) {
        Cont_Name = cont_Name;
    }

    public void setCont_Number(String cont_Number) {
        Cont_Number = cont_Number;
    }

    public void setCont_Image(String cont_Image) {
        Cont_Image = cont_Image;
    }

    public void setCat_Image(String cat_Image) {
        Cat_Image = cat_Image;
    }

    public void setCont_Address(String cont_Address) {
        Cont_Address = cont_Address;
    }

    public void setCat_Name(String cat_Name) {
        Cat_Name = cat_Name;
    }

    public void setCont_Affiliation(String cont_Affiliation) { Cont_Affiliation = cont_Affiliation; }

    public void setCont_Services(String cont_Services) {
        Cont_Services = cont_Services;
    }

    public void setCont_Hmo(String cont_Hmo) {
        Cont_Hmo = cont_Hmo;
    }

    public void setCont_Offers(String cont_Offers) {
        Cont_Offers = cont_Offers;
    }

    public void setCont_Service_Sched(String cont_Service_Sched) {
        Cont_Service_Sched = cont_Service_Sched;
    }

    protected CallDirectoryItems(Parcel in) {
        Cont_Name = in.readString();
        Cont_Number = in.readString();
        Cont_Image = in.readString();
        Cat_Image = in.readString();
        Cat_Name = in.readString();
        Cont_Address = in.readString();
        Cont_Affiliation = in.readString();
        Cont_Services = in.readString();
        Cont_Hmo = in.readString();
        Cont_Offers = in.readString();
        Cont_Service_Sched = in.readString();

    }

   public static final Parcelable.Creator<CallDirectoryItems> CREATOR = new Parcelable.Creator<CallDirectoryItems>() {
        @Override
        public CallDirectoryItems createFromParcel(Parcel in) {
            return new CallDirectoryItems(in);
        }

        @Override
        public CallDirectoryItems[] newArray(int size) {
            return new CallDirectoryItems[size];
        }
    };



}