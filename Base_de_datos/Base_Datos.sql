CREATE DATABASE PDesign;
USE PDesign;
create TABLE empleado(   
    rfc char(13) PRIMARY KEY,
    nombre_e VARCHAR(30) NOT NULL,
    ape_pat VARCHAR(25) not NULL,
    ape_mat VARCHAR(25) NOT NULL,
    sexo enum('Masc','Fem') DEFAULT 'Masc',
    fecha_nacimiento DATE NOT NULL,
    telefono INT(15) null,
    looker TINYINT(3) not null,
    area set("Diseño","Finanzas","Experimental","Manufactura","Vigilante"));
/*registro empleados exixtentes*/
create TABLE usuario(  
    id TINYINT(3) unsigned auto_increment PRIMARY KEY,
    username VARCHAR(15) UNIQUE not null,
    contraseña CHAR(8) not null,
    tipo enum("User", "Admin") DEFAULT "User",
    rfc_e char(13) not null,
    FOREIGN KEY(rfc_e) REFERENCES empleado(rfc));
/*usuarios acorde a empleados*/
create table registro( 
    Id_r TINYINT(3) unsigned auto_increment PRIMARY KEY,
    FECHA TIMESTAMP DEFAULT NOW(),
    entrada time not null,
    salida_c TIME not null,
    entrada_c TIME not null,
    salida TIME not null,
    RETARDO TINYINT(2) UNSIGNED NULL,
    PERMISO DATE NULL,
    MOTIVO VARCHAR(40) NULL,
    ID_U CHAR(5) NOT NULL);
/*registro de usuarios diario*/
CREATE TABLE Existencias( 
    codigo INT(5) ZEROFILL unsigned auto_increment PRIMARY KEY,
    clase enum("Herramienta", "Material") not null,
    nombre VARCHAR(25) not null,
    especificaciones text null,
    cantidad INT(6) NOT NULL,
    unidad_m enum("Metros","Galones","Kilos","Litros", "Piezas")null,
    Tipo_m enum("Solido","Liquido") NULL);
/*materiales y herramientas*/
Create table proveedor(
    id_proveedor TINYINT(3) unsigned auto_increment PRIMARY KEY,
    Nombre_P VARCHAR(50) not null,
    tel_p int(20) null,
    CORREO VARCHAR(50) null);
/*registro proveedores*/
Create table compras( 
    num_reg int(5) unsigned auto_increment PRIMARY KEY,
    NO_FACT VARCHAR(20) not null,
    cod_e INT(5) ZEROFILL unsigned NOT NULL,
    CANTIDAD TINYINT(3) unsigned NOT NULL,
    FECHA DATE NOT NULL,
    TIPO_PAGO ENUM('EFECTIVO','CREDITO','DEBITO','OTRO') NOT NULL,
    COSTO DECIMAL(10,2) NOT NULL,
    FACT ENUM('FISICA','CORREO','PENDIENTE') NOT NULL,
    NO_PROVE TINYINT(3) UNSIGNED NOT NULL,
    FOREIGN KEY (cod_e) REFERENCES Existencias(codigo),
    FOREIGN KEY (NO_PROVE) REFERENCES proveedor(id_proveedor));
/*registro de las compras*/
create table proyecto( 
    id_p INT(5) unsigned auto_increment PRIMARY KEY,
    nombre_proyecto VARCHAR(45) not null,
    caracteristicas text not null,
    tipo enum("demo","diseño","prototipo","industrial", "otro")not null);
/*Registro de proyectos*/
create table usar(
    no_proyecto int(5) unsigned not null,
    requerido int(5) zerofill unsigned not null,
    cantidad int(6) unsigned not null,
    id_user TINYINT(3) unsigned not NULL,
    FOREIGN KEY(id_user) REFERENCES usuario(id),
    foreign key(no_proyecto) references proyecto(id_p),
    foreign key(requerido) references Existencias(codigo));
/*asignacion de materiales y cantidad a usar*/

insert into usuario (username, contraseña, tipo, rfc_e) values
    ('diego', '********','Admin','dibp981212012'),
    ('mario', '********','User','manl990109901'),
    ('alex', '********', 'User','alhp990918h12');

insert INTO empleado (rfc, nombre_e,ape_pat,ape_mat,sexo, fecha_nacimiento, looker, area)VALUES
    ('dibp981212012','Diego','Bolaños','Pardo','Masc',1998-12-12,3,5),
    ('manl990109901','Mario Alberto','Nieto','Lopez','Masc', 1999-01-09,4,1),
    ('alhp990918h12','Alexis','Hernandez','Perez',1, 1999-09-18, 2, 3);

INSERT INTO Existencias (clase, nombre, especificaciones, cantidad) VALUES
    (1, 'Desarmador', 'Cruz', 10),
    (1, 'Martillo', 'Trupper', 2);
INSERT INTO Existencias (clase, nombre, especificaciones, cantidad, unidad_m, Tipo_m) VALUES
    (2, 'Tubo', 'rojo 1x1', 3, 1, 1),
    (2, 'Bateria', 'Nissan', 1, 5,1);
INSERT INTO proveedor (Nombre_P)VALUES
   ('Jose Perez'),
   ('Carlos Cerezo'),
   ('Jorge Hernandez');
INSERT INTO compras (NO_FACT, cod_e, CANTIDAD, FECHA, TIPO_PAGO, COSTO, FACT, NO_PROVE) VALUES 
    ('1234567891011', 1, 12, 2018-12-12, 2, 120, 3, 1);
Insert into proyecto (nombre_proyecto, caracteristicas, tipo)VALUES
    ('ANDREA', 'AUTO ELECTRICO', 3),
    ('Fenix', 'Remodelacion de auto electrico', 1),
    ('COCACOLA', 'Elaboracion de maqueta de prototipos de autos', 3);
Insert INTO usar VALUES
   (1, 1, 2),
   (2, 4, 1);

create procedure asignar(no_p int, num_e int, cant int)
    INSERT INTO usar VALUES
    (no_p, num_e, cant),
    where no_p = no_proyecto, num_e = requerido, cant = cantidad;

create PROCEDURE (id_p int)
    select * from usar,
    where id_p = no_proyecto;
