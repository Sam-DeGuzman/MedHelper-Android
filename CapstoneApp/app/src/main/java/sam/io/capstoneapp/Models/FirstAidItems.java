package sam.io.capstoneapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class FirstAidItems implements Parcelable {
    private int aidImageResource;
    private String aidName;

    public FirstAidItems(int FAImageResource,String FAName){
        aidImageResource = FAImageResource;
        aidName = FAName;
    }

    protected FirstAidItems(Parcel in) {
        aidImageResource = in.readInt();
        aidName = in.readString();
    }

    public static final Creator<FirstAidItems> CREATOR = new Creator<FirstAidItems>() {
        @Override
        public FirstAidItems createFromParcel(Parcel in) {
            return new FirstAidItems(in);
        }

        @Override
        public FirstAidItems[] newArray(int size) {
            return new FirstAidItems[size];
        }
    };

    public int getAidImageResource(){
        return aidImageResource;
    }

    public String getAidName(){
        return aidName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(aidImageResource);
        dest.writeString(aidName);
    }
}
