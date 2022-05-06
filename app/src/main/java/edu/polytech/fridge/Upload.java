package edu.polytech.fridge;

public class Upload {
    private String imageUrl;
    private String name;

    public Upload(){}

    public Upload(String name,String imageUrl){
        this.imageUrl=imageUrl;
        this.name =name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
