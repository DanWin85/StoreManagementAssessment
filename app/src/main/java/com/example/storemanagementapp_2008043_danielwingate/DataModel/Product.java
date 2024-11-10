package com.example.storemanagementapp_2008043_danielwingate.DataModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Products")
public class Product {
    @PrimaryKey(autoGenerate = true)
    private long productID;
    @ColumnInfo(name = "Product_Name")
    private String productName;
    @ColumnInfo(name = "Product_Description")
    private String productDescription;
    @ColumnInfo(name = "Product_Price")
    private long productUnitPrice;


    public Product(long productID, String productName, String productDescription, long productUnitPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productUnitPrice = productUnitPrice;
    }

    public long getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public long getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductUnitPrice(long productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }
}
