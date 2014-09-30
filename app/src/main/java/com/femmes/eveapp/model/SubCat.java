package com.femmes.eveapp.model;

public class SubCat {
    private String subCat, productUrl;
    private int catid;

    public SubCat() {
    }

    public SubCat(String name, String productUrl, int catid) {
        this.subCat = name;
        this.productUrl = productUrl;
        this.catid=catid;
    }

    public String getSubCat() {
        return subCat;
    }
    public void setSubCat(String name) {
        this.subCat = name;
    }

    public String getProductUrl() {
        return productUrl;
    }
    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public int getCatid() {
        return catid;
    }
    public void setCatid(int catid) {
        this.catid= catid;
    }
}
