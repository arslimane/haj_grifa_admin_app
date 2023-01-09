package com.example.myapplication;

public class product {
   public String title;
    public String tag;
    public  String comment;
    public String image;
    public  String price;
    public String reference;
    public String description;
    public String release_date;

    public product(String title, String tag, String comment, String image, String price) {
        this.title = title;
        this.tag = tag;
        this.comment = comment;
        this.image = image;
        this.price = price;
    }
}
