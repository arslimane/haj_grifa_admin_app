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

     public product(){

    }


    public product(String title, String tag, String comment, String image, String price, String reference, String description, String release_date) {
        this.title = title;
        this.tag = tag;
        this.comment = comment;
        this.image = image;
        this.price = price;
        this.reference = reference;
        this.description = description;
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
