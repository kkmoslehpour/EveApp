package com.femmes.eveapp.model;

import java.util.ArrayList;

public class SubCat {
    private String subCat, productUrl;

    public SubCat() {
    }

    public SubCat(String name, String productUrl) {
        this.subCat = name;
        this.productUrl = productUrl;
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
}
