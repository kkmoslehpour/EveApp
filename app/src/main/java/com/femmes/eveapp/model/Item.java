package com.femmes.eveapp.model;

public class Item {
    private String image, name;
    private double price;

    public Item(){
    }
    public Item(String name, String image, double price){
        this.name=name;
        this.image=image;
        this.price=price;
    }

    public String getName(){ return name;}
    public void setName(String name){this.name=name;}

    public String getImage(){return image;}
    public void setImage(String image){this.image=image;}

    public double getPrice(){return price;}
    public void setPrice(double price){this.price=price;}
}
