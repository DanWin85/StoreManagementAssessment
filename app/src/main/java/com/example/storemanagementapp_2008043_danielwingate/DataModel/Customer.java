package com.example.storemanagementapp_2008043_danielwingate.DataModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity(tableName = "Customers")
public class Customer {
    @PrimaryKey (autoGenerate = true)
    private int customerID;
    @ColumnInfo(name = "Customer_First_Name")
    private String customerFirstName;
    @ColumnInfo(name = "Customer_Last_Name")
    private String customerLastName;
    @ColumnInfo(name = "Customer_Birth_Date")
    private Date customerDateOfBirth;
    @ColumnInfo(name = "Customer_Address")
    private String customerAddress;
    @ColumnInfo(name = "Customer_Suburb")
    private String customerSuburb;
    @ColumnInfo(name = "Customer_City")
    private String customerCity;
    @ColumnInfo(name = "Customer_ZIPCode")
    private String customerZIP;
    @ColumnInfo(name = "Customer_Registration_Date")
    private Date customerRegistrationDate;

    public Customer(int customerID, String customerFirstName, String customerLastName, Date customerDateOfBirth,
                    String customerAddress, String customerSuburb, String customerCity, String customerZIP,
                    Date customerRegistrationDate) {
        this.customerID = customerID;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerDateOfBirth = customerDateOfBirth;
        this.customerAddress = customerAddress;
        this.customerSuburb = customerSuburb;
        this.customerCity = customerCity;
        this.customerZIP = customerZIP;
        this.customerRegistrationDate = customerRegistrationDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public Date getCustomerDateOfBirth() {
        return customerDateOfBirth;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerSuburb() {
        return customerSuburb;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public String getCustomerZIP() {
        return customerZIP;
    }

    public Date getCustomerRegistrationDate() {
        return customerRegistrationDate;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public void setCustomerDateOfBirth(Date customerDateOfBirth) {
        this.customerDateOfBirth = customerDateOfBirth;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerSuburb(String customerSuburb) {
        this.customerSuburb = customerSuburb;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public void setCustomerZIP(String customerZIP) {
        this.customerZIP = customerZIP;
    }

    public void setCustomerRegistrationDate(Date customerRegistrationDate) {
        this.customerRegistrationDate = customerRegistrationDate;
    }
}
