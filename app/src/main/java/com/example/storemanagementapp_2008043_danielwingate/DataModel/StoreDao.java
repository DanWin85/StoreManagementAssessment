package com.example.storemanagementapp_2008043_danielwingate.DataModel;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface StoreDao {

    @Insert
    long insertCustomer(Customer customer);

    @Update
    void updateCustomer(Customer customer);

    @Delete
    void deleteCustomer(Customer customer);

    @Query("SELECT * FROM Customers WHERE customerID = :customerId")
    Customer getCustomerById(int customerId);

    @Query("SELECT * FROM Customers")
    List<Customer> getAllCustomers();

    @Query("SELECT * FROM Customers WHERE Customer_City = :city")
    List<Customer> getCustomersByCity(String city);

    // Product CRUD operations
    @Insert
    long insertProduct(Product product);

    @Update
    void updateProduct(Product product);

    @Delete
    void deleteProduct(Product product);

    @Query("SELECT * FROM Products WHERE productID = :productId")
    Product getProductById(long productId);

    @Query("SELECT * FROM Products")
    List<Product> getAllProducts();

    @Query("SELECT * FROM Products WHERE Product_Price <= :maxPrice")
    List<Product> getProductsByMaxPrice(long maxPrice);

    // CustomerOrder operations
    @Insert
    long insertOrder(CustomerOrder order);

    @Delete
    void deleteOrder(CustomerOrder order);

    @Query("SELECT * FROM `Customer_Orders` WHERE orderID = :orderId")
    CustomerOrder getOrderById(long orderId);

    @Query("SELECT * FROM `Customer_Orders` WHERE Customer_ID = :customerId")
    List<CustomerOrder> getOrdersByCustomer(int customerId);

    @Query("SELECT * FROM `Customer_Orders` WHERE Order_Date BETWEEN :startDate AND :endDate")
    List<CustomerOrder> getOrdersByDateRange(Date startDate, Date endDate);

    // OrderDetail operations
    @Insert
    void insertOrderDetail(OrderDetail orderDetail);

    @Update
    void updateOrderDetail(OrderDetail orderDetail);

    @Delete
    void deleteOrderDetail(OrderDetail orderDetail);

    @Query("SELECT * FROM `Order-Details` WHERE Order_ID = :orderId")
    List<OrderDetail> getOrderDetailsByOrderId(long orderId);

}
