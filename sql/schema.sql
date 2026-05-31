CREATE DATABASE hotel_management;
USE hotel_management;

CREATE TABLE User (
    user_name VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100),
    f_name VARCHAR(50),
    l_name VARCHAR(50),
    phone_num VARCHAR(20),
    role VARCHAR(20) DEFAULT "Guest"
);
 
CREATE TABLE Manager (   
    manager_id INT PRIMARY KEY AUTO_INCREMENT, #100
    user_name VARCHAR(50),
    FOREIGN KEY (user_name) REFERENCES User(user_name)
)AUTO_INCREMENT = 100;

CREATE TABLE Waiter (
    waiter_id INT PRIMARY KEY AUTO_INCREMENT, #200
    user_name VARCHAR(50),
    FOREIGN KEY (user_name) REFERENCES User(user_name)
)AUTO_INCREMENT = 200;

CREATE TABLE Guest (
    guest_id INT PRIMARY KEY AUTO_INCREMENT, #300
    user_name VARCHAR(50),
    manager_id INT,
    FOREIGN KEY (user_name) REFERENCES User(user_name),
    FOREIGN KEY (manager_id) REFERENCES Manager(manager_id)
)AUTO_INCREMENT = 300;

CREATE TABLE Room (
    room_num INT PRIMARY KEY AUTO_INCREMENT, #400
    room_status VARCHAR(50),
    class_name VARCHAR(50) CHECK (class_name IN ('Single', 'Double', 'Suite')),
    bed_type VARCHAR(50) CHECK(bed_type IN ('Single Bed', 'Twin Beds', 'Queen Bed', 'King Bed')),
    room_price DECIMAL(10, 2)
) AUTO_INCREMENT = 400;

CREATE TABLE Booking (
    booking_id INT PRIMARY KEY AUTO_INCREMENT, #500
    checkin_date DATE, 
    checkout_date DATE,
    room_num INT,
    FOREIGN KEY (room_num) REFERENCES Room (room_num)
) AUTO_INCREMENT = 500;

CREATE TABLE Manager_booking (
    manager_id INT,
    booking_id INT,
    PRIMARY KEY (manager_id, booking_id),
    FOREIGN KEY (manager_id) REFERENCES Manager (manager_id),
    FOREIGN KEY (booking_id) REFERENCES Booking (booking_id)
);

CREATE TABLE Guest_booking (
    guest_id INT,
    booking_id INT,
    PRIMARY KEY (guest_id, booking_id),
    FOREIGN KEY (guest_id) REFERENCES Guest (guest_id),
    FOREIGN KEY (booking_id) REFERENCES Booking (booking_id)
);

CREATE TABLE ServiceAdding (
    service_id INT PRIMARY KEY AUTO_INCREMENT, #600
    service_type VARCHAR(50),
    service_price DECIMAL(10,2)
)AUTO_INCREMENT = 600;

CREATE TABLE Service (
    service_id INT,
    booking_id INT,
    service_status VARCHAR(20) DEFAULT 'Added',
    FOREIGN KEY (service_id) REFERENCES ServiceAdding(service_id),
    FOREIGN KEY (booking_id) REFERENCES Booking(booking_id)
);

CREATE TABLE Waiter_service (
    waiter_id INT,
    service_id INT,
    PRIMARY KEY (waiter_id, service_id),
    FOREIGN KEY (waiter_id) REFERENCES Waiter(waiter_id),
    FOREIGN KEY (service_id) REFERENCES Service(service_id)
);

CREATE TABLE Payment (
    payment_id INT PRIMARY KEY AUTO_INCREMENT, #700
    booking_id INT,
    total_amount DECIMAL(10,2),
    payment_date DATE,
    payment_status VARCHAR(20) DEFAULT 'Unpaid',
    FOREIGN KEY (booking_id) REFERENCES Booking(booking_id)
)AUTO_INCREMENT = 700;
