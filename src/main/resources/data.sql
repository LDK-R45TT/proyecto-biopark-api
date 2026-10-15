-- USERS
INSERT INTO users (created_at, name, email, password, role) VALUES
('2026-10-04 11:00:00', 'Nahel', 'nmanzur@gmail.com',  '$2a$16$IAGO9qIYSTzcthXhA4MtyeMd81NsfWHKUNR21wZMOw73A8muqnJEu', 'ADMIN'),
('2026-10-04 11:00:00', 'Matias', 'matiasrosell_12@admin.com', '$2a$16$XkIi6R/.TvMBL6Hbxjymae5r0eFL5N51Va3O.yItKj0XnxbN4fQFy', 'ADMIN'),
('2026-10-05 11:00:00', 'Ines', 'ines_aranguri_88@yahoo.com.pe', '$2a$16$mWxchgDknzgnjUcleug.euq3QxcaNIlcZYdzyLENOtSW95T7rorLO', 'USER'),
('2026-10-05 11:00:00', 'Genaro', 'gbustamante94@gmail.com', '$2a$16$NmWEOegAe4ENrPCqnQqJDeJvf.DqqTGVXVpIko8ROuCDYG3Di84f2', 'USER');

-- VISITORS
INSERT INTO visitors (nombre, apellido, dni, edad) VALUES
('Cinthia', 'Tarazona', '28456123', 29),
('Genaro', 'Bustamante', '31987654', 34),
('Analia', 'Valoppi', '25341987', 41),
('Ines', 'Aranguri', '40123456', 22),
('Gustavo', 'Ardaya', '33654321', 31),
('Facundo', 'Rivero', '29789456', 38),
('Oderay', 'Maldonado', '35159357', 27),
('Matias', 'Rosell', '32456789', 30),
('Justina', 'Valdevit', '38951753', 25),
('Lautaro', 'Tozonni', '27123987', 45);

-- ENVIRONMENTS
INSERT INTO environments (name, description, active) VALUES
('Fosa De Reptiles','Tanques de lodo industrializados y cámaras especiales que resguardan cocodrilos e iguanas.', true ),
('Estación Arácnida', 'Laboratorio microclimatizado hermético para tarántulas y escorpiones emperador...', true),
('Bahía Delfín', 'Gran piscina conectada a compuertas hidráulicas para delfines y orcas.', true),
('Cueva del Pulpo', 'Tanque de luz tenue adaptado para pulpos gigantes y calamares.', true),
('Jaula de Vuelo', 'Aviario gigante de malla reforzada para contemplar tucanes y aves exoticas.', true);

-- RATES
INSERT INTO rates (nombre, precio, descripcion) VALUES
('Pase Recluta Silver', 15.00,'Ingreso sin filas para el menor. Otorga el préstamo de binoculares tácticos de alta resistencia y participación en simulacros guiados de reconocimiento de huellas.'),
('Pase Élite Golden Kids', 20.00,'Acceso VIP infantil escoltado por oficiales del Biohive. Incluye ingreso restringido al sector de incubadoras, registro fotográfico con animales en fase inicial y diploma oficial de Investigador Junior.'),
('Pase Operativo Base', 10.00,'Ingreso regular por fila general. Permite la visualización del espécimen a través de los blindajes perimetrales del sector seleccionado.');

-- TICKETS
/*INSERT INTO tickets (visitor_id_fk, ticket_active, rate_id_fk, environment_id_fk) VALUES
(1, 1, 2, 1),
(2, 1, 1, 3),
(3, 1, 3, 2),
(4, 0, 2, 5),
(5, 1, 3, 4),
(6, 1, 1, 1),
(7, 0, 3, 3),
(8, 0, 1, 5);*/