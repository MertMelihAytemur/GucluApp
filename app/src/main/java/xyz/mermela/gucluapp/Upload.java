package xyz.mermela.gucluapp;

public class Upload {
    private String imageUrl;
    private String location;
    private String username;

    public Upload() {

    }

    public Upload(String imageUrl,String location,String username) {

        this.imageUrl = imageUrl;
        this.location = location;
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
