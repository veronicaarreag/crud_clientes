create database db_empresa;

use db_empresa;

create table clientes(
	id_cliente int auto_increment,
    nit varchar(9),
    nombres varchar(60),
    apellidos varchar(60),
    direccion varchar(100),
    telefono varchar(8),
    fecha_nacimiento date,
    primary key(id_cliente)
);

create table puestos(
	id_puesto smallint auto_increment,
    puesto varchar(40),
    primary key(id_puesto)
);

create table empleados(
	idempleados int auto_increment,
    codigo varchar(20),
    nombres varchar(60),
    apellidos varchar(60),
    direccion varchar(100),
    telefono varchar(8),
    fecha_nacimiento date,
    id_puesto smallint,
    primary key(idempleados),
    constraint id_puesto_puesto_empleados foreign key(id_puesto) references puestos(id_puesto) on delete no action on update cascade
);

