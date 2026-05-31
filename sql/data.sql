INSERT INTO User(user_name, password, f_name, l_name, phone_num, role)
VALUES ('ab123', '1234', 'Abebe', 'Kebede', '+251983924472', 'Manager');

INSERT INTO Manager(user_name)
VALUES ('ab123');

INSERT INTO User(user_name, password, f_name, l_name, phone_num, role)
VALUES ('ba123', '1234', 'Bekele', 'Kebede', '+251983924472', 'Waiter');

INSERT INTO Waiter(user_name)
VALUES ('ba123');

INSERT INTO Room(room_status, class_name, bed_type, room_price)
VALUES ('Available', 'Suite', 'Single Bed', 1000.00),
('Available', 'Suite', 'Single Bed', 1000.00);

INSERT INTO ServiceAdding(service_type, service_price)
VALUES('Breakfast', 200.00);
