package sam.io.capstoneapp.Models;

public class FirstAidModel {
    private int Image;
    private String Title;
    private String StepName;
    private String Desc;

    public FirstAidModel(int image, String title,String stepName, String desc) {
        this.Image = image;
        this.Title = title;
        this.StepName = stepName;
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

    public String getStepName() {
        return StepName;
    }

    public void setStepName(String stepName) {
        StepName = stepName;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }


}
