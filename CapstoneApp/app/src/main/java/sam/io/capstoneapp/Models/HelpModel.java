package sam.io.capstoneapp.Models;

public class HelpModel {
    private int Image;
    private String Title,Desc;

    public HelpModel(int image, String title, String desc) {
        this.Image = image;
        this.Title = title;
        this.Desc = desc;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}
