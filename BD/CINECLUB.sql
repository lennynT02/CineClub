CREATE DATABASE CINECLUB
GO

USE CINECLUB

CREATE TABLE CLIENTE(
    ID_CLI NUMERIC(3,0) NOT NULL PRIMARY KEY,
    NOM_CLI VARCHAR(30) NOT NULL,
    APE_CLI VARCHAR(30) NOT NULL,
    CORREO_CLI VARCHAR(30) NOT NULL,
    CONTRA_CLI VARCHAR(30) NOT NULL,
)

CREATE TABLE PELICULA(
    ID_PEL VARCHAR(4) NOT NULL PRIMARY KEY,
    NOM_PEL VARCHAR(30) NOT NULL,
    GEN_PEL VARCHAR(30) NOT NULL,
    DUR_PEL NUMERIC(3,0) NOT NULL,
    DES_PEL VARCHAR(30) NOT NULL,
    FECHA_PEL DATE NOT NULL,
    DIR_PEL VARCHAR(30) NOT NULL,
    CALI_PEL NUMERIC(1,0)
)   

CREATE TABLE HORARIO(
    ID_HOR NUMERIC(3,0) NOT NULL PRIMARY KEY,
    ID_PEL VARCHAR(4) NOT NULL,
    HORA TIME NOT NULL,
    SALA NUMERIC(2,0) NOT NULL,
    FECHA DATE NOT NULL,
    CUPO_TOTAL NUMERIC(3,0) NOT NULL,
    CUPO_DISP NUMERIC(3,0) NOT NULL,
    FOREIGN KEY (ID_PEL) REFERENCES PELICULA(ID_PEL)
)

CREATE TABLE RESERVA(
    ID_RES NUMERIC(3,0) NOT NULL PRIMARY KEY,
    ID_CLI NUMERIC(3,0) NOT NULL,
    ID_HOR NUMERIC(3,0) NOT NULL,
    NUM_RES NUMERIC(3,0) NOT NULL,
    FOREIGN KEY (ID_CLI) REFERENCES CLIENTE(ID_CLI),
    FOREIGN KEY (ID_HOR) REFERENCES HORARIO(ID_HOR)
)

CREATE TABLE HISTORIAL_RESERVA(
    ID_HIS NUMERIC(3,0) NOT NULL PRIMARY KEY,
    ID_CLI NUMERIC(3,0) NOT NULL,
    ID_RES NUMERIC(3,0) NOT NULL,
    FECHA_RES DATE NOT NULL,
    FOREIGN KEY (ID_CLI) REFERENCES CLIENTE(ID_CLI),
    FOREIGN KEY (ID_RES) REFERENCES RESERVA(ID_RES)
)

CREATE TABLE ESTADISTICAS_VENTAS(
    ID_ESTV NUMERIC(3,0) NOT NULL PRIMARY KEY,
    MONTO_VEN NUMERIC(3,0) NOT NULL,
    FECHA DATE NOT NULL
)

CREATE TABLE SALAS(
    ID_ESTS NUMERIC(3,0) NOT NULL PRIMARY KEY,
    NUM_ASIENTOS NUMERIC(2,0) NOT NULL,
    FECHA DATE NOT NULL
)