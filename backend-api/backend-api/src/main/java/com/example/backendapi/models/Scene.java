package com.example.backendapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("scenes")
public class Scene
{

    @Id
    private String id;
    private String title;
    private String description;
    private String sellingPrice;
    private String category;
    private String image;
    private String rentingPrice;
    private Boolean isFeatured;
    private Boolean isHero;

    //Constructor Method


    public Scene()
    {

    }

    public Scene(String id, String title, String description, String sellingPrice, String category,
                  String image, String rentingPrice, Boolean isFeatured, Boolean isHero)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.sellingPrice = sellingPrice;
        this.category = category;
        this.image = image;
        this.rentingPrice = rentingPrice;
        this.isFeatured = isFeatured;
        this.isHero = isHero;
    }

    //Getter and Setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRentingPrice() {
        return rentingPrice;
    }

    public void setRentingPrice(String rentingPrice) {
        this.rentingPrice = rentingPrice;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Boolean getIsHero() {
        return isHero;
    }

    public void setIsHero(Boolean isHero) {
        this.isHero = isHero;
    }

    //ToString Method
    @Override
    public String toString() {
        return "Scene{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", sellingPrice='" + sellingPrice + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", rentingPrice='" + rentingPrice + '\'' +
                ", isFeatured='" + isFeatured + '\'' +
                ", isHero='" + isHero + '\'' +
                '}';
    }

}
