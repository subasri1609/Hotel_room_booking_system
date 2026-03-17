package com.hotel.dao;

import com.hotel.model.Room;
import com.hotel.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public void addRoom(Room room) {
        String query = "INSERT INTO rooms(room_type, price, is_available) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, room.getRoomType());
            ps.setDouble(2, room.getPrice());
            ps.setBoolean(3, room.isAvailable());
            ps.executeUpdate();
            System.out.println("Room added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Room> getAvailableRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT room_id, room_type, price, is_available FROM rooms WHERE is_available = true";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Room room = new Room();
                room.setRoomId(rs.getInt("room_id"));
                room.setRoomType(rs.getString("room_type"));
                room.setPrice(rs.getDouble("price"));
                room.setAvailable(rs.getBoolean("is_available"));
                rooms.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public boolean isRoomAvailable(int roomId) {
        String query = "SELECT is_available FROM rooms WHERE room_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, roomId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("is_available");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateAvailability(int roomId, boolean available) {
        String query = "UPDATE rooms SET is_available = ? WHERE room_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setBoolean(1, available);
            ps.setInt(2, roomId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
