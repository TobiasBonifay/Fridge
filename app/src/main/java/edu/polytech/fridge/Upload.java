package edu.polytech.fridge;

public class Upload {
    private String imageUrl;

    public Upload(){}

    public Upload(String imageUrl){
        this.imageUrl=imageUrl;

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
