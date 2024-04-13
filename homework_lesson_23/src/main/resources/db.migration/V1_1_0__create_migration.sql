CREATE TABLE IF NOT EXISTS managers (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(30),
    number_phone VARCHAR(13)
);

CREATE TABLE IF NOT EXISTS clients (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(30),
    number_phone VARCHAR(13)
);

CREATE TABLE IF NOT EXISTS flat (
    id SERIAL PRIMARY KEY,
    number_room INTEGER,
    area VARCHAR(25),
    price MONEY,
    manager_id INTEGER,

    FOREIGN KEY (manager_id) REFERENCES managers(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS rental_inquiries (
     id SERIAL PRIMARY KEY,
     client_id INTEGER,
     flat_id INTEGER,

    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE,
    FOREIGN KEY (flat_id) REFERENCES flat(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS lease_experience (
     id SERIAL PRIMARY KEY,
     rent_start DATE,
     rent_finish DATE,
     client_id INTEGER,
     flat_id INTEGER,

    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE,
    FOREIGN KEY (flat_id) REFERENCES flat(id) ON DELETE CASCADE
);