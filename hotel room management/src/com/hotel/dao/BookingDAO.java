package com.hotel.dao;

import com.hotel.model.Booking;
import com.hotel.util.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class BookingDAO {

    public void addBooking(Booking booking) {
        String query = "INSERT INTO bookings(customer_id, room_id, booking_date) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, booking.getCustomerId());
            ps.setInt(2, booking.getRoomId());
            ps.setDate(3, Date.valueOf(booking.getBookingDate()));
            ps.executeUpdate();
            System.out.println("Booking created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
