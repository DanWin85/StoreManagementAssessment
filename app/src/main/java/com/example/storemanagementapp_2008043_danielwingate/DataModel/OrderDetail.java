package com.example.storemanagementapp_2008043_danielwingate.DataModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (tableName = "Order-Details",
foreignKeys = {
        @ForeignKey(entity = CustomerOrder.class, parentColumns = "orderID", childColumns = "Order_ID"),
        @ForeignKey(entity = Product.class, parentColumns = "productID", childColumns = "Product_ID")
    }
)
public class OrderDetail {
    @PrimaryKey
    @ColumnInfo(name = "Order_ID", index = true)
    private long orderID;
    @ColumnInfo(name = "Product_ID", index = true )
    private long productID;
    private int orderDetailQuantity;

    public OrderDetail(long orderID, long productID, int orderDetailQuantity) {
        this.orderID = orderID;
        this.productID = productID;
        this.orderDetailQuantity = orderDetailQuantity;
    }

    public long getOrderID() {
        return orderID;
    }

    public long getProductID() {
        return productID;
    }

    public int getOrderDetailQuantity() {
        return orderDetailQuantity;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public void setOrderDetailQuantity(int orderDetailQuantity) {
        this.orderDetailQuantity = orderDetailQuantity;
    }
}
