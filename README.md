🏨 Hotel Room Booking Management System (JDBC)

A simple Java-based Hotel Room Booking Management System developed using JDBC, MySQL, and Eclipse.
This project demonstrates core concepts of database connectivity, CRUD operations, and layered architecture.

📌 Features

➕ Add new rooms

👀 View available rooms

🧾 Book rooms for customers

❌ Cancel bookings (optional/extendable)

🔍 Check room availability

🛠️ Technologies Used

Java (JDK 8+)

JDBC (Java Database Connectivity)

MySQL Database

Eclipse IDE

🗂️ Project Structure
com.hotel
│
├── dao        # Database access logic (JDBC)
│   ├── RoomDAO.java
│   ├── CustomerDAO.java
│   └── BookingDAO.java
│
├── model      # POJO classes
│   ├── Room.java
│   ├── Customer.java
│   └── Booking.java
│
├── util       # Database connection
│   └── DBConnection.java
│
└── main       # Main application
    └── MainApp.java
🗄️ Database Setup
1. Create Database
CREATE DATABASE hotel_db;
USE hotel_db;
2. Create Tables
CREATE TABLE rooms (
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    room_type VARCHAR(50),
    price DOUBLE,
    is_available BOOLEAN
);

CREATE TABLE customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    phone VARCHAR(15)
);

CREATE TABLE bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    room_id INT,
    booking_date DATE,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);
⚙️ Setup Instructions
1. Clone the repository
git clone https://github.com/your-username/hotel-room-booking.git
2. Import into Eclipse

Open Eclipse

File → Import → Existing Projects into Workspace

Select the project folder

3. Add MySQL Connector

Download MySQL Connector/J

Right-click project → Build Path → Add External JAR

Select the .jar file

4. Configure Database Connection

Update in DBConnection.java:

private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
▶️ Run the Project

Right-click MainApp.java

Click Run As → Java Application

📸 Sample Output
1. Add Room
2. View Rooms
3. Book Room
4. Exit
Enter choice:
🚀 Future Enhancements

🔐 User authentication (Login/Register)

📅 Date-wise booking system

🖥️ GUI using Swing/JavaFX

🌐 Web version using Spring Boot

🎯 Learning Outcomes

JDBC connectivity with MySQL

PreparedStatement usage

Layered architecture (DAO, Model, Utility)

Basic CRUD operations

🤝 Contributing

Feel free to fork this repository and enhance the project!

📄 License

This project is for educational purposes.
