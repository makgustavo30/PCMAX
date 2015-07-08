/*
Created: 20/05/2015
Modified: 30/06/2015
Model: PostgreSQL 9.2
Database: PostgreSQL 9.2
*/


-- Create tables section -------------------------------------------------

-- Table usuario

CREATE TABLE "usuario"(
 "id_usuario" Integer NOT NULL,
 "nombre" Character varying(25) NOT NULL,
 "apellido_paterno" Character varying(25) NOT NULL,
 "apellido_materno" Character varying(25) NOT NULL,
 "calle" Character varying(20),
 "avenida" Character varying(20),
 "numero" Character varying(20),
 "colonia" Character varying(25),
 "codigo_postal" Integer,
 "ciudad" Character varying(25) NOT NULL,
 "telefono" Character varying(20) NOT NULL,
 "email" Character varying(30) NOT NULL,
 "nombre_usuario" Character varying(25) NOT NULL,
 "contrasenia" Character varying(25) NOT NULL,
 "tipo" Character varying(20) NOT NULL,
 "estatus" Character(1) DEFAULT 's' NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table usuario

ALTER TABLE "usuario" ADD CONSTRAINT "Key1" PRIMARY KEY ("id_usuario")
;

ALTER TABLE "usuario" ADD CONSTRAINT "email_usuario" UNIQUE ("email")
;

ALTER TABLE "usuario" ADD CONSTRAINT "nombre_usuario" UNIQUE ("nombre_usuario")
;

ALTER TABLE "usuario" ADD CONSTRAINT "contrasenia" UNIQUE ("contrasenia")
;

-- Table venta

CREATE TABLE "venta"(
 "id_venta" Integer NOT NULL,
 "vendedor" Integer,
 "fecha_hora" Timestamp with time zone NOT NULL,
 "id_cliente" Integer,
 "iva" Numeric(6,2) NOT NULL,
 "garantia" Text NOT NULL,
 "terminal" Numeric NOT NULL,
 "id_check_equipo" Integer
)
WITH (OIDS=FALSE)
;

-- Create indexes for table venta

CREATE INDEX "IX_Relationship37" ON "venta" ("vendedor")
;

CREATE INDEX "IX_Relationship40" ON "venta" ("id_cliente")
;

CREATE INDEX "IX_Relationship45" ON "venta" ("id_check_equipo")
;

-- Add keys for table venta

ALTER TABLE "venta" ADD CONSTRAINT "Key2" PRIMARY KEY ("id_venta")
;

-- Table producto

CREATE TABLE "producto"(
 "id_producto" Integer NOT NULL,
 "id_categoria" Integer,
 "id_marca" Integer,
 "numero_de_parte" Character varying(25) NOT NULL,
 "existencias" Numeric NOT NULL,
 "precio_de_compra" Numeric(6,2) NOT NULL,
 "precio_1" Numeric(6,2) NOT NULL,
 "precio_2" Numeric(6,2) NOT NULL,
 "precio_3" Numeric(6,2) NOT NULL,
 "descripcion" Text NOT NULL,
 "stock_maximo" Integer NOT NULL,
 "stock_minimo" Integer NOT NULL,
 "estatus" Character(1) DEFAULT 's' NOT NULL
)
WITH (OIDS=FALSE)
;

-- Create indexes for table producto

CREATE INDEX "IX_Relationship29" ON "producto" ("id_categoria")
;

CREATE INDEX "IX_Relationship47" ON "producto" ("id_marca")
;

-- Add keys for table producto

ALTER TABLE "producto" ADD CONSTRAINT "Key4" PRIMARY KEY ("id_producto")
;

-- Table detalle_garantia

CREATE TABLE "detalle_garantia"(
 "id_detalle_garantia" Integer NOT NULL,
 "id_producto" Integer,
 "id_venta" Integer,
 "cantidad" Integer NOT NULL,
 "precio" Numeric(6,2) NOT NULL,
 "estatus" Character varying(25) NOT NULL
)
WITH (OIDS=FALSE)
;

-- Create indexes for table detalle_garantia

CREATE INDEX "IX_Relationship35" ON "detalle_garantia" ("id_producto")
;

CREATE INDEX "IX_Relationship39" ON "detalle_garantia" ("id_venta")
;

-- Add keys for table detalle_garantia

ALTER TABLE "detalle_garantia" ADD CONSTRAINT "Key5" PRIMARY KEY ("id_detalle_garantia")
;

-- Table categoria

CREATE TABLE "categoria"(
 "id_categoria" Integer NOT NULL,
 "nombre_categoria" Character varying(25) NOT NULL,
 "estatus" Character(1) DEFAULT 's' NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table categoria

ALTER TABLE "categoria" ADD CONSTRAINT "Key6" PRIMARY KEY ("id_categoria")
;

ALTER TABLE "categoria" ADD CONSTRAINT "nombre_categoria" UNIQUE ("nombre_categoria")
;

-- Table servicio

CREATE TABLE "servicio"(
 "id_servicio" Integer NOT NULL,
 "tipo_de_servicio" Character varying(25) NOT NULL,
 "precio_1" Numeric(30,2),
 "precio_2" Numeric(30,2),
 "precio_3" Numeric(30,2),
 "estatus" Character(1) DEFAULT 's' NOT NULL,
 "dias_de_garantia" Integer NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table servicio

ALTER TABLE "servicio" ADD CONSTRAINT "Key7" PRIMARY KEY ("id_servicio")
;

-- Table detalle_venta

CREATE TABLE "detalle_venta"(
 "id_detalle_venta" Integer NOT NULL,
 "id_producto" Integer,
 "id_venta" Integer,
 "cantidad" Integer NOT NULL,
 "precio" Numeric(6,2) NOT NULL,
 "id_detalle_producto" Integer,
 "iva" Numeric(6,2) NOT NULL
)
WITH (OIDS=FALSE)
;

-- Create indexes for table detalle_venta

CREATE INDEX "IX_Relationship24" ON "detalle_venta" ("id_venta")
;

CREATE INDEX "IX_Relationship36" ON "detalle_venta" ("id_producto")
;

CREATE INDEX "IX_Relationship48" ON "detalle_venta" ("id_detalle_producto")
;

-- Add keys for table detalle_venta

ALTER TABLE "detalle_venta" ADD CONSTRAINT "Key8" PRIMARY KEY ("id_detalle_venta")
;

-- Table cliente

CREATE TABLE "cliente"(
 "id_cliente" Integer NOT NULL,
 "rfc" Character varying(20) NOT NULL,
 "nombre" Character varying(25) NOT NULL,
 "apellido_paterno" Character varying(25) NOT NULL,
 "apellido_materno" Character varying(25) NOT NULL,
 "calle" Character varying(20),
 "avenida" Character varying(20),
 "numero" Character varying(10),
 "colonia" Character varying(25),
 "codigo_postal" Numeric NOT NULL,
 "ciudad" Character varying(25) NOT NULL,
 "telefono" Character varying(20) NOT NULL,
 "email" Character varying(30) NOT NULL,
 "estatus" Character(1) DEFAULT 's' NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table cliente

ALTER TABLE "cliente" ADD CONSTRAINT "Key9" PRIMARY KEY ("id_cliente")
;

ALTER TABLE "cliente" ADD CONSTRAINT "rfc_cliente" UNIQUE ("rfc")
;

ALTER TABLE "cliente" ADD CONSTRAINT "email_cliente" UNIQUE ("email")
;

-- Table detalle_compra

CREATE TABLE "detalle_compra"(
 "id_detalle_compra" Integer NOT NULL,
 "id_compra" Integer,
 "id_producto" Integer,
 "cantidad" Integer NOT NULL,
 "precio" Numeric(6,2) NOT NULL,
 "precio_compra_1" Numeric(6,6) NOT NULL,
 "precio_compra_2" Numeric(6,2) NOT NULL,
 "precio_compra_3" Numeric(6,2) NOT NULL,
 "id_detalle_producto" Integer
)
WITH (OIDS=FALSE)
;

-- Create indexes for table detalle_compra

CREATE INDEX "IX_Relationship32" ON "detalle_compra" ("id_compra")
;

CREATE INDEX "IX_Relationship33" ON "detalle_compra" ("id_producto")
;

CREATE INDEX "IX_Relationship54" ON "detalle_compra" ("id_detalle_producto")
;

-- Add keys for table detalle_compra

ALTER TABLE "detalle_compra" ADD CONSTRAINT "Key10" PRIMARY KEY ("id_detalle_compra")
;

-- Table compra

CREATE TABLE "compra"(
 "id_compra" Integer NOT NULL,
 "fecha_hora_compra" Timestamp with time zone NOT NULL,
 "fecha_hora_registro" Timestamp with time zone NOT NULL,
 "id_proveedor" Integer,
 "id_usuario" Integer
)
WITH (OIDS=FALSE)
;

-- Create indexes for table compra

CREATE INDEX "IX_Relationship31" ON "compra" ("id_proveedor")
;

CREATE INDEX "IX_Relationship55" ON "compra" ("id_usuario")
;

-- Add keys for table compra

ALTER TABLE "compra" ADD CONSTRAINT "Key11" PRIMARY KEY ("id_compra")
;

-- Table proveedor

CREATE TABLE "proveedor"(
 "id_proveedor" Integer NOT NULL,
 "nombre_empresa" Character varying(25) NOT NULL,
 "rfc" Character varying(20) NOT NULL,
 "calle" Character varying(20),
 "avenida" Character varying(20),
 "numero" Character varying(10),
 "colonia" Character varying(25),
 "codigo_postal" Numeric,
 "ciudad" Character varying(25) NOT NULL,
 "telefono" Character varying(20),
 "email" Character varying(30) NOT NULL,
 "estatus" Character(1) DEFAULT 's' NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table proveedor

ALTER TABLE "proveedor" ADD CONSTRAINT "Key12" PRIMARY KEY ("id_proveedor")
;

ALTER TABLE "proveedor" ADD CONSTRAINT "email_proveedor" UNIQUE ("email")
;

ALTER TABLE "proveedor" ADD CONSTRAINT "rfc_proveedor" UNIQUE ("rfc")
;

-- Table contacto_proveedor

CREATE TABLE "contacto_proveedor"(
 "id_contacto_proveedor" Integer NOT NULL,
 "nombre" Character varying(25) NOT NULL,
 "apellido_paterno" Character varying(25) NOT NULL,
 "apellido_materno" Character varying(25) NOT NULL,
 "telefono" Character varying(20) NOT NULL,
 "email" Character varying(30) NOT NULL,
 "estatus" Character(1) DEFAULT 's' NOT NULL,
 "id_proveedor" Integer NOT NULL
)
WITH (OIDS=FALSE)
;

-- Create indexes for table contacto_proveedor

CREATE INDEX "IX_Relationship63" ON "contacto_proveedor" ("id_proveedor")
;

-- Add keys for table contacto_proveedor

ALTER TABLE "contacto_proveedor" ADD CONSTRAINT "Key13" PRIMARY KEY ("id_contacto_proveedor")
;

-- Table detalle_servicio

CREATE TABLE "detalle_servicio"(
 "id_detalle_servicio" Integer NOT NULL,
 "id_venta" Integer,
 "id_servicio" Integer,
 "cantidad" Integer NOT NULL,
 "precio" Numeric(6,2) NOT NULL
)
WITH (OIDS=FALSE)
;

-- Create indexes for table detalle_servicio

CREATE INDEX "IX_Relationship41" ON "detalle_servicio" ("id_venta")
;

CREATE INDEX "IX_Relationship42" ON "detalle_servicio" ("id_servicio")
;

-- Add keys for table detalle_servicio

ALTER TABLE "detalle_servicio" ADD CONSTRAINT "Key14" PRIMARY KEY ("id_detalle_servicio")
;

-- Table check_equipo

CREATE TABLE "check_equipo"(
 "id_check_equipo" Integer NOT NULL,
 "tipo_equipo" Character varying(20) NOT NULL,
 "sistema_equipo" Character varying(20) NOT NULL,
 "sistema_operativo" Character varying(20) NOT NULL,
 "danios_presentados" Text NOT NULL,
 "falla_detectada" Text NOT NULL,
 "diagnostico" Text NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table check_equipo

ALTER TABLE "check_equipo" ADD CONSTRAINT "Key15" PRIMARY KEY ("id_check_equipo")
;

-- Table detalle_producto

CREATE TABLE "detalle_producto"(
 "id_detalle_producto" Integer NOT NULL,
 "numero_serie" Character varying(25) NOT NULL,
 "vendido" Character(1) NOT NULL,
 "id_producto" Integer
)
WITH (OIDS=FALSE)
;

-- Create indexes for table detalle_producto

CREATE INDEX "IX_Relationship46" ON "detalle_producto" ("id_producto")
;

-- Add keys for table detalle_producto

ALTER TABLE "detalle_producto" ADD CONSTRAINT "Key16" PRIMARY KEY ("id_detalle_producto")
;

ALTER TABLE "detalle_producto" ADD CONSTRAINT "numero_serie" UNIQUE ("numero_serie")
;

-- Table marcas

CREATE TABLE "marcas"(
 "id_marca" Integer NOT NULL,
 "nombre_marca" Character varying(20) NOT NULL,
 "estatus" Character(1) DEFAULT 's' NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table marcas

ALTER TABLE "marcas" ADD CONSTRAINT "Key17" PRIMARY KEY ("id_marca")
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE "detalle_venta" ADD CONSTRAINT "Relationship24" FOREIGN KEY ("id_venta") REFERENCES "venta" ("id_venta") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "producto" ADD CONSTRAINT "Relationship29" FOREIGN KEY ("id_categoria") REFERENCES "categoria" ("id_categoria") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "compra" ADD CONSTRAINT "Relationship31" FOREIGN KEY ("id_proveedor") REFERENCES "proveedor" ("id_proveedor") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "detalle_compra" ADD CONSTRAINT "Relationship32" FOREIGN KEY ("id_compra") REFERENCES "compra" ("id_compra") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "detalle_compra" ADD CONSTRAINT "Relationship33" FOREIGN KEY ("id_producto") REFERENCES "producto" ("id_producto") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "detalle_garantia" ADD CONSTRAINT "Relationship35" FOREIGN KEY ("id_producto") REFERENCES "producto" ("id_producto") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "detalle_venta" ADD CONSTRAINT "Relationship36" FOREIGN KEY ("id_producto") REFERENCES "producto" ("id_producto") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "venta" ADD CONSTRAINT "Relationship37" FOREIGN KEY ("vendedor") REFERENCES "usuario" ("id_usuario") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "detalle_garantia" ADD CONSTRAINT "Relationship39" FOREIGN KEY ("id_venta") REFERENCES "venta" ("id_venta") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "venta" ADD CONSTRAINT "Relationship40" FOREIGN KEY ("id_cliente") REFERENCES "cliente" ("id_cliente") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "detalle_servicio" ADD CONSTRAINT "Relationship41" FOREIGN KEY ("id_venta") REFERENCES "venta" ("id_venta") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "detalle_servicio" ADD CONSTRAINT "Relationship42" FOREIGN KEY ("id_servicio") REFERENCES "servicio" ("id_servicio") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "venta" ADD CONSTRAINT "Relationship45" FOREIGN KEY ("id_check_equipo") REFERENCES "check_equipo" ("id_check_equipo") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "detalle_producto" ADD CONSTRAINT "Relationship46" FOREIGN KEY ("id_producto") REFERENCES "producto" ("id_producto") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "producto" ADD CONSTRAINT "Relationship47" FOREIGN KEY ("id_marca") REFERENCES "marcas" ("id_marca") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "detalle_venta" ADD CONSTRAINT "Relationship48" FOREIGN KEY ("id_detalle_producto") REFERENCES "detalle_producto" ("id_detalle_producto") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "detalle_compra" ADD CONSTRAINT "Relationship54" FOREIGN KEY ("id_detalle_producto") REFERENCES "detalle_producto" ("id_detalle_producto") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "compra" ADD CONSTRAINT "Relationship55" FOREIGN KEY ("id_usuario") REFERENCES "usuario" ("id_usuario") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "contacto_proveedor" ADD CONSTRAINT "Relationship63" FOREIGN KEY ("id_proveedor") REFERENCES "proveedor" ("id_proveedor") ON DELETE NO ACTION ON UPDATE NO ACTION
;





