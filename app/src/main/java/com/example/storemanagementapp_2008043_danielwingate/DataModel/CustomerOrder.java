package com.example.storemanagementapp_2008043_danielwingate.DataModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "Customer_Orders",
        foreignKeys = {
            @ForeignKey(entity = Customer.class, parentColumns = "customerID", childColumns = "Customer_ID")
        }
)
public class CustomerOrder {
    @PrimaryKey( autoGenerate = true)
    private long orderID;
    @ColumnInfo(name = "Order_Date")
    private Date orderDate;
    @ColumnInfo(name = "Customer_ID", index = true)
    private int customerID;

    public CustomerOrder(long orderID, Date orderDate, int customerID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
    }

    public long getOrderID() {
        return orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
