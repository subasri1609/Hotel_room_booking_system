package com.hotel.main;

import com.hotel.dao.BookingDAO;
import com.hotel.dao.CustomerDAO;
import com.hotel.dao.RoomDAO;
import com.hotel.model.Booking;
import com.hotel.model.Customer;
import com.hotel.model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoomDAO roomDAO = new RoomDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        BookingDAO bookingDAO = new BookingDAO();

        while (true) {
            System.out.println("\n1. Add Room");
            System.out.println("2. Add Customer");
            System.out.println("3. Book Room");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = readInt(sc);
            switch (choice) {
                case 1:
                    Room room = new Room();
                    System.out.print("Enter Room Type: ");
                    room.setRoomType(readLine(sc));
                    System.out.print("Enter Price: ");
                    room.setPrice(readDouble(sc));
                    room.setAvailable(true);
                    roomDAO.addRoom(room);
                    break;
                case 2:
                    Customer customer = new Customer();
                    System.out.print("Enter Customer Name: ");
                    customer.setName(readLine(sc));
                    System.out.print("Enter Phone: ");
                    customer.setPhone(readLine(sc));
                    customerDAO.addCustomer(customer);
                    break;
                case 3:
                    List<Room> availableRooms = roomDAO.getAvailableRooms();
                    if (availableRooms.isEmpty()) {
                        System.out.println("No available rooms.");
                        break;
                    }
                    System.out.println("Available rooms:");
                    for (Room r : availableRooms) {
                        System.out.println("ID: " + r.getRoomId()
                                + " | Type: " + r.getRoomType()
                                + " | Price: " + r.getPrice());
                    }
                    System.out.print("Enter Room ID to book: ");
                    int roomId = readInt(sc);
                    if (!roomDAO.isRoomAvailable(roomId)) {
                        System.out.println("Room not available.");
                        break;
                    }
                    System.out.print("Enter Customer ID: ");
                    int customerId = readInt(sc);
                    System.out.print("Enter Booking Date (YYYY-MM-DD): ");
                    LocalDate bookingDate = LocalDate.parse(readLine(sc));

                    Booking booking = new Booking();
                    booking.setRoomId(roomId);
                    booking.setCustomerId(customerId);
                    booking.setBookingDate(bookingDate);
                    bookingDAO.addBooking(booking);
                    roomDAO.updateAvailability(roomId, false);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Enter a valid number: ");
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    private static double readDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            sc.nextLine();
            System.out.print("Enter a valid number: ");
        }
        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }

    private static String readLine(Scanner sc) {
        String line = sc.nextLine();
        while (line.trim().isEmpty()) {
            line = sc.nextLine();
        }
        return line;
    }
}
