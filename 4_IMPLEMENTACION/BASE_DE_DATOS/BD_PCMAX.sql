CREATE TABLE usuario(id_usuario INT PRIMARY KEY NOT NULL,nombre VARCHAR(25) NOT NULL,apellido_paterno VARCHAR(25) NOT NULL,apellido_materno VARCHAR(25) NOT NULL,calle 

VARCHAR(20) NULL,avenida VARCHAR(20) NULL,numero VARCHAR(10) NULL,colonia VARCHAR(25) NULL,codigo_postal INT NULL,ciudad VARCHAR(25) NOT NULL,telefono VARCHAR(20) 

DEFAULT '000-000-00-00' NOT NULL,email VARCHAR(30) UNIQUE NOT NULL,nombre_usuario VARCHAR(25) UNIQUE NOT NULL,contrasenia VARCHAR(25) UNIQUE NOT NULL,tipo VARCHAR(20) 

NOT NULL,estatus BIT(1) NOT NULL);

CREATE TABLE categoria(id_categoria INT PRIMARY KEY,nombre_categoria VARCHAR(25) UNIQUE NOT NULL,estatus BIT(1) NOT NULL);

CREATE TABLE detalle_producto(id_detalle_producto INT PRIMARY KEY,numero_serie VARCHAR(25) UNIQUE NOT NULL);

CREATE TABLE contacto_proveedor(id_contacto_proveedor INT PRIMARY KEY NOT NULL,nombre VARCHAR(25) NOT NULL,apellido_paterno VARCHAR(25) NOT NULL,apellido_materno 

VARCHAR(25) NOT NULL,calle VARCHAR(20) NULL,avenida VARCHAR(20) NULL,numero VARCHAR(10) NULL,colonia VARCHAR(25) NULL,codigo_postal INT NULL,ciudad VARCHAR(25) NOT 

NULL,telefono VARCHAR(20) DEFAULT '000-000-00-00' NOT NULL,email VARCHAR(30) NOT NULL,estatus BIT(1) NOT NULL);

CREATE TABLE proveedor(id_proveedor INT PRIMARY KEY NOT NULL,nombre_empresa VARCHAR(25) NOT NULL,rfc VARCHAR(20) UNIQUE NOT NULL,calle VARCHAR(20) NULL,avenida 

VARCHAR(20) NULL,numero VARCHAR(10) NULL,colonia VARCHAR(25) NULL,codigo_postal INT NULL,ciudad VARCHAR(25) NOT NULL,telefono VARCHAR(20) DEFAULT '000-000-00-00' NOT 

NULL,email VARCHAR(30) UNIQUE NOT NULL,estatus BIT(1) NOT NULL,id_contacto_proveedor INT  REFERENCES contacto_proveedor (id_contacto_proveedor) NOT NULL);

CREATE TABLE compra(id_compra INT PRIMARY KEY,id_proveedor INT REFERENCES proveedor (id_proveedor) NOT NULL,fecha_compra timestamp without time zone NOT 

NULL,fecha_registro timestamp without time zone NOT NULL);

CREATE TABLE producto(id_producto INT PRIMARY KEY NOT NULL,id_categoria INT REFERENCES categoria (id_categoria) NOT NULL,numero_de_serie INT REFERENCES 

detalle_producto (id_detalle_producto) NOT NULL,numero_de_parte VARCHAR(25) NOT NULL,existencias NUMERIC NOT NULL,precio_de_compra DECIMAL(18,2) NOT NULL,precio_1 

DECIMAL(18,2) NOT NULL,precio_2 DECIMAL(18,2) NOT NULL,precio_3 DECIMAL(18,2) NOT NULL,descripcion TEXT NOT NULL,estatus BIT(1) NOT NULL);

CREATE TABLE servicio(id_servicio INT PRIMARY KEY NOT NULL,tipo_de_servicio VARCHAR(25) NOT NULL,precio_1 DECIMAL(18,2) NOT NULL,precio_2 DECIMAL(18,2) NOT 

NULL,precio_3 DECIMAL(18,2) NOT NULL,estatus BIT(1) NOT NULL);

CREATE TABLE cliente(id_cliente INT PRIMARY KEY NOT NULL,rfc VARCHAR(20) UNIQUE NOT NULL,nombre VARCHAR(25) NOT NULL,apellido_paterno VARCHAR(25) NOT 

NULL,apellido_materno VARCHAR(25) NOT NULL,calle VARCHAR(20) NULL,avenida VARCHAR(20) NULL,numero VARCHAR(10) NULL,colonia VARCHAR(25) NULL,codigo_postal INT 

NULL,ciudad VARCHAR(25) NOT NULL,telefono VARCHAR(20) DEFAULT '000-000-00-00' NOT NULL,email VARCHAR(30) UNIQUE NOT NULL,estatus BIT(1) NOT NULL);

CREATE TABLE check_usuario(id_check_usuario INT PRIMARY KEY NOT NULL,tipo_equipo VARCHAR(20) UNIQUE NOT NULL,sistema_equipo VARCHAR(25) NOT NULL,sistema_operativo VARCHAR(25) NOT NULL,danios_presentados TEXT NOT NULL,falla_detectada TEXT NOT NULL,diagnostico TEXT NOT NULL);

CREATE TABLE venta(id_venta INT PRIMARY KEY,vendedor INT  REFERENCES usuario (id_usuario),fecha_hora timestamp without time zone DEFAULT now() NOT NULL,id_cliente INT 

REFERENCES cliente (id_cliente),iva DECIMAL(18,2) NOT NULL,garantia TEXT NOT NULL,terminal NUMERIC NOT NULL,id_check_usuario INT REFERENCES check_usuario (id_check_usuario));

CREATE TABLE detalle_venta(id_detalle_venta INT PRIMARY KEY,id_producto INT REFERENCES producto (id_producto) NOT NULL,id_venta INT REFERENCES venta (id_venta) NOT 

NULL,cantidad INT NOT NULL,precio DECIMAL(18,2) NOT NULL);

CREATE TABLE detalle_garantia(id_detalle_garantia INT PRIMARY KEY,id_producto INT REFERENCES producto (id_producto) NOT NULL,id_venta INT REFERENCES venta (id_venta) 

NOT NULL,cantidad INT NOT NULL,precio DECIMAL(18,2) NOT NULL);

CREATE TABLE detalle_compra(id_detalle_compra INT PRIMARY KEY,id_compra INT REFERENCES compra (id_compra) NOT NULL,id_producto INT REFERENCES producto (id_producto) 

NOT NULL,cantidad INT NOT NULL,precio DECIMAL(18,2) NOT NULL);

CREATE TABLE detalle_servicio(id_detalle_servicio INT PRIMARY KEY,id_venta INT REFERENCES venta (id_venta) NOT NULL,id_servicio INT REFERENCES servicio (id_servicio) 

NOT NULL,cantidad INT NOT NULL,precio DECIMAL(18,2) NOT NULL);

