INSERT INTO
    USUARIO (username, password, email, rol)
VALUES
    (
        'ofertante1',
        'password1',
        'ofertante1@example.com',
        'OFE'
    ),
    (
        'consumidor1',
        'password2',
        'consumidor1@example.com',
        'CON'
    ),
    (
        'admin1',
        'adminpass',
        'admin1@example.com',
        'ADMIN'
    );

INSERT INTO
    TIPO (nombre)
VALUES
    ('Deportes'),
    ('Cultura'),
    ('Ocio Nocturno');

INSERT INTO
    CONSUMIDOR (
        nombre,
        apellido,
        fecha_nacimiento,
        email,
        username
    )
VALUES
    (
        'Juan',
        'Pérez',
        '1990-05-15',
        'juan.perez@example.com',
        'consumidor1'
    ),
    (
        'Ana',
        'Gómez',
        '1985-11-22',
        'ana.gomez@example.com',
        NULL
    );

-- sin cuenta de usuario
INSERT INTO
    OFERTANTE (nombre, apellido, email, username)
VALUES
    (
        'Pedro',
        'Martínez',
        'pedro.martinez@example.com',
        'ofertante1'
    ),
    ('Laura', 'López', 'laura.lopez@example.com', NULL);

-- sin cuenta de usuario
INSERT INTO
    ACTIVIDAD (
        nombre,
        duracion,
        precio,
        aireLibre,
        fecha,
        mas18,
        ofertante_id,
        tipo_id
    )
VALUES
    (
        'Clase de fútbol',
        90,
        25.00,
        true,
        '2024-11-01',
        false,
        1,
        1
    ),
    (
        'Visita a museo',
        120,
        15.50,
        false,
        '2024-12-15',
        false,
        1,
        2
    ),
    (
        'Tour de bares',
        180,
        50.00,
        true,
        '2024-11-20',
        true,
        1,
        3
    );

INSERT INTO
    CONSUMO_ACTIVIDAD (consumidor_id, actividad_id)
VALUES
    (1, 1),
    (1, 2);