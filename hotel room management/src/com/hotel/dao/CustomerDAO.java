package com.hotel.dao;

import com.hotel.model.Customer;
import com.hotel.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerDAO {

    public int addCustomer(Customer customer) {
        String query = "INSERT INTO customers(name, phone) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhone());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Customer added with ID: " + id);
                    return id;
                }
            }
            System.out.println("Customer added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
