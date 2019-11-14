package com.proxima.antinoassignment.utility;

public class CardObject {
    String imageURL , name , age , location;

    public CardObject(String imageURL, String name, String age, String location) {
        this.imageURL = imageURL;
        this.name = name;
        this.age = age;
        this.location = location;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }
}
