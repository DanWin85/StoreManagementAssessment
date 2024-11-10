package com.example.storemanagementapp_2008043_danielwingate.DataModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "Customer-Orders",
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
}
