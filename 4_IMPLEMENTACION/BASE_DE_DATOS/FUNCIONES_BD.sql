/* Funcion actualizar categorias*/
CREATE OR REPLACE FUNCTION fn_actualizarcategoria(
    clave integer,
    nuevonombre character varying)
  RETURNS void AS
$BODY$
declare maximo integer;
begin
update categoria set nombre_categoria=nuevonombre where id_categoria= clave;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;

/* Funcion actualizar marcas*/
CREATE OR REPLACE FUNCTION fn_actualizarmarca(
    clave integer,
    nuevonombre character varying)
  RETURNS void AS
$BODY$
declare maximo integer;
begin
update marcas set nombre_marca=nuevonombre where id_marca= clave;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;

/* Funcion actualizar productos*/
CREATE OR REPLACE FUNCTION fn_actualizarproductos(
    clavemarca integer,
    clavecategoria integer,
    _codigo integer,
    _numerodeparte character varying,
    _precio1 real,
    _precio2 real,
    _precio3 real,
    _descripcion text,
    _stockmaximo integer,
    _stockminimo integer)
  RETURNS void AS
$BODY$
begin
update producto set id_categoria=clavecategoria, id_marca=clavemarca, numero_de_parte=_numerodeparte, precio_1=_precio1, precio_2=_precio2, 
precio_3=_precio3, descripcion=_descripcion, stock_maximo=_stockmaximo, stock_minimo=_stockminimo where id_producto= _codigo;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;


/* Funcion actualizar servicios*/
CREATE OR REPLACE FUNCTION fn_actualizarservicio(
    clave integer,
    nuevonombre character varying,
    nuevoprecio1 real,
    nuevoprecio2 real,
    nuevoprecio3 real,
    nuevodiasgarantia integer)
  RETURNS void AS
$BODY$
declare maximo integer;
begin
update servicio set tipo_de_servicio=nuevonombre, precio_1=nuevoprecio1, precio_2=nuevoprecio2, precio_3=nuevoprecio3, dias_de_garantia=nuevodiasgarantia where id_servicio= clave;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;

/* Funcion agregar categoria*/

CREATE OR REPLACE FUNCTION fn_agregarcategoria(nombre character varying)
  RETURNS void AS
$BODY$
declare maximo integer;
begin
select (max (id_categoria)+1) into maximo from categoria;
insert into categoria(id_categoria, nombre_categoria)
values (maximo, nombre);
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;

/* Funcion agregar marca*/

CREATE OR REPLACE FUNCTION fn_agregarmarca(nombre character varying)
  RETURNS void AS
$BODY$
declare maximo integer;
begin
select (max (id_marca)+1) into maximo from marcas;
insert into marcas(id_marca, nombre_marca)
values (maximo, nombre);
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;

/* Funcion agregar producto*/
CREATE OR REPLACE FUNCTION fn_agregarproducto(
    clavemarca integer,
    clavecategoria integer,
    _codigo integer,
    _numerodeparte character varying,
    _precio1 real,
    _precio2 real,
    _precio3 real,
    _descripcion character varying,
    _stockmaximo integer,
    _stockminimo integer)
  RETURNS void AS
$BODY$
begin
insert into producto(id_producto, id_categoria, id_marca, numero_de_parte, precio_1, precio_2, precio_3, descripcion,
stock_maximo, stock_minimo)
values (_codigo, clavecategoria, clavemarca, _numerodeparte, _precio1, _precio2, _precio3, _descripcion, _stockmaximo, _stockminimo);
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;


/* Funcion agregar proveedor*/

CREATE OR REPLACE FUNCTION fn_agregarproveedor(
    nombreempresa character varying,
    _rfc character varying,
    _calle character varying,
    _avenida character varying,
    _numero character varying,
    _colonia character varying,
    _codigopostal integer,
    _ciudad character varying,
    _telefono1 character varying,
    _email1 character varying,
    _nombre character varying,
    apellidopa character varying,
    apellidoma character varying,
    _celular character varying,
    _emailcontacto character varying)
  RETURNS void AS
$BODY$
declare maximo integer;
declare total integer;
declare maximoProveedor integer;
begin
select count (id_proveedor)into total from proveedor;

if total = 0 then maximo:=1;
else
select (max(id_proveedor)+1)into maximo from proveedor;
end if;

select count (id_contacto_proveedor)into total from contacto_proveedor;
if total = 0 then maximoProveedor:=1;
else
select (max(id_contacto_proveedor)+1)into maximoProveedor from contacto_proveedor;
end if;

 
insert into proveedor (id_proveedor, nombre_empresa, rfc, calle, avenida, numero, colonia,
codigo_postal, ciudad, telefono,email)
values (maximo,nombreempresa, _rfc, _calle, _avenida, _numero, _colonia, _codigopostal, _ciudad, _telefono1, _email1);

insert into contacto_proveedor(id_contacto_proveedor, id_proveedor, nombre, apellido_paterno, apellido_materno, 
celular, email_contacto)

values (maximoProveedor, maximo, _nombre , apellidopa, apellidoma, _celular, _emailcontacto);

end;

$BODY$
  LANGUAGE plpgsql VOLATILE;

/* Funcion agregar servicio*/
CREATE OR REPLACE FUNCTION fn_agregarservicio(
    tiposervicio character varying,
    precio1 real,
    precio2 real,
    precio3 real,
    diasgarantia integer)
  RETURNS void AS
$BODY$
declare maximo integer;
declare total integer;
begin
select count(id_servicio) into total from servicio;
if total = 0 then maximo:=1;
else
select (max (id_servicio)+1) into maximo from servicio;
end if;
insert into servicio(id_servicio, tipo_de_servicio, precio_1, precio_2, precio_3, dias_de_garantia)
values (maximo, tiposervicio, precio1, precio2, precio3, diasgarantia);
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;

/* Funcion eliminar categoria*/
CREATE OR REPLACE FUNCTION fn_eliminarcategoria(clave integer)
  RETURNS void AS
$BODY$
begin
update categoria set estatus='n' where id_categoria= clave;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;


/* Funcion eliminar contacto proveedor*/
CREATE OR REPLACE FUNCTION fn_eliminarcontactoproveedor(clave integer)
  RETURNS void AS
$BODY$
begin
update contacto_proveedor set estatus='n' where id_contacto_proveedor= clave;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;

/* Funcion eliminar marca*/
CREATE OR REPLACE FUNCTION fn_eliminarmarca(clave integer)
  RETURNS void AS
$BODY$
begin
update marcas set estatus='n' where id_marca= clave;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;

/* Funcion eliminar producto*/
CREATE OR REPLACE FUNCTION fn_eliminarproducto(clave integer)
  RETURNS void AS
$BODY$
begin
update producto set estatus='n' where id_producto= clave;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;

/* Funcion eliminar servicio*/
CREATE OR REPLACE FUNCTION fn_eliminarservicio(clave integer)
  RETURNS void AS
$BODY$
begin
update servicio set estatus='n' where id_servicio= clave;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;

/*funcion para llamar productos*/
CREATE OR REPLACE FUNCTION fn_producto()
  RETURNS TABLE(idproducto integer, numeroparte character varying, existencia numeric, precio1 numeric, precio2 numeric, precio3 numeric, descripciones text, stockmaximo integer, stockminimo integer, nombremarca character varying, nombrecategoria character varying, idcategoria integer, idmarca integer) AS
$BODY$
begin
return query 
select id_producto, numero_de_parte, existencias, precio_1, precio_2, precio_3, descripcion, stock_maximo, stock_minimo, 
marcas.nombre_marca, categoria.nombre_categoria, categoria.id_categoria, marcas.id_marca from producto
	inner join categoria on producto.id_categoria = categoria.id_categoria
		inner join marcas on producto.id_marca = marcas.id_marca 
		where producto.estatus = 's' order by id_producto;
end
$BODY$
  LANGUAGE plpgsql VOLATILE;

/*funcion para llamar productos inactivos*/
CREATE OR REPLACE FUNCTION fn_productoinactivo()
  RETURNS TABLE(idproducto integer, numeroparte character varying, existencia numeric, precio1 numeric, precio2 numeric, precio3 numeric, descripciones text, stockmaximo integer, stockminimo integer, nombremarca character varying, nombrecategoria character varying, idcategoria integer, idmarca integer) AS
$BODY$
begin
return query 
select id_producto, numero_de_parte, existencias, precio_1, precio_2, precio_3, descripcion, stock_maximo, stock_minimo, 
marcas.nombre_marca, categoria.nombre_categoria, categoria.id_categoria, marcas.id_marca from producto
	inner join categoria on producto.id_categoria = categoria.id_categoria
		inner join marcas on producto.id_marca = marcas.id_marca 
		where producto.estatus = 'n' order by id_producto;
end
$BODY$
  LANGUAGE plpgsql VOLATILE;

/*funcion para llamar proveedores*/
CREATE OR REPLACE FUNCTION fn_proveedor()
  RETURNS TABLE(nombrecompleto text, nombrecontacto character varying, apaterno character varying, amaterno character varying, cel character varying, emailcontacto character varying, idproveedor integer, nombreempresa character varying, registrofc character varying, calles character varying, avenidas character varying, num character varying, col character varying, cp numeric, ciudad character varying, tel character varying, emailempresa character varying) AS
$BODY$
begin
return query 
 select nombre||' '|| apellido_paterno||' '|| apellido_materno as nombrecompleto, nombre, apellido_paterno, apellido_materno, celular, email_contacto,proveedor.id_proveedor, proveedor.nombre_empresa, 
 proveedor.rfc, proveedor.calle, proveedor.avenida, proveedor.numero, proveedor.colonia, proveedor.codigo_postal, 
 proveedor.ciudad, proveedor.telefono, proveedor.email from contacto_proveedor
	inner join proveedor on contacto_proveedor.id_proveedor = proveedor.id_proveedor
	where contacto_proveedor.estatus ='s' order by id_contacto_proveedor;
end
$BODY$
  LANGUAGE plpgsql VOLATILE;

/*funcion para llamar proveedores inactivos*/
CREATE OR REPLACE FUNCTION fn_proveedorinactivo()
  RETURNS TABLE(nombrecompleto text, nombrecontacto character varying, apaterno character varying, amaterno character varying, cel character varying, emailcontacto character varying, idproveedor integer, nombreempresa character varying, registrofc character varying, calles character varying, avenidas character varying, num character varying, col character varying, cp numeric, ciudad character varying, tel character varying, emailempresa character varying) AS
$BODY$
begin
return query 
 select nombre||' '|| apellido_paterno||' '|| apellido_materno as nombrecompleto, nombre, apellido_paterno, apellido_materno, celular, email_contacto,proveedor.id_proveedor, proveedor.nombre_empresa, 
 proveedor.rfc, proveedor.calle, proveedor.avenida, proveedor.numero, proveedor.colonia, proveedor.codigo_postal, 
 proveedor.ciudad, proveedor.telefono, proveedor.email from contacto_proveedor
	inner join proveedor on contacto_proveedor.id_proveedor = proveedor.id_proveedor
	where contacto_proveedor.estatus ='n' order by id_contacto_proveedor;
end
$BODY$
  LANGUAGE plpgsql VOLATILE;

/*funciones extra*/
CREATE OR REPLACE FUNCTION fn_agregarcheckequipo(
    tipo_equipo character varying,
    sistema_equipo character varying,
    sistema_operativo character varying,
    danios_presentados character varying,
    falla_detectada character varying,
    diagnostico character varying)
  RETURNS void AS
$BODY$
declare maximo integer;
begin 

select (max(id_check_equipo)+1) into maximo
  from check_equipo;

insert into check_equipo (id_check_equipo,tipo_equipo,sistema_equipo,sistema_operativo,danios_presentados,falla_detectada,diagnostico)
values (maximo,tipo_equipo,sistema_equipo,sistema_operativo,danios_presentados,falla_detectada,diagnostico);

end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
CREATE OR REPLACE FUNCTION fn_agregarcliente(
    rfc character varying,
    nombre character varying,
    apellido_paterno character varying,
    apellido_materno character varying,
    calle character varying,
    avenida character varying,
    numero character varying,
    colonia character varying,
    codigo_postal integer,
    ciudad character varying,
    telefono character varying,
    email character varying)
  RETURNS void AS
$BODY$
declare maximo integer;
begin 

select (max(id_cliente)+1) into maximo
  from cliente;

insert into cliente (id_cliente,rfc,nombre,apellido_paterno,apellido_materno,calle,avenida,numero,colonia,codigo_postal,ciudad,telefono,email)
values (maximo,rfc,nombre,apellido_paterno,apellido_materno,calle,avenida,numero,colonia,codigo_postal,ciudad,telefono,email);

end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
CREATE OR REPLACE FUNCTION fn_agregarusuario(
    nombre_usuario character varying,
    contrasenia character varying,
    nombre character varying,
    apellido_paterno character varying,
    apellido_materno character varying,
    calle character varying,
    avenida character varying,
    numero character varying,
    colonia character varying,
    codigo_postal integer,
    ciudad character varying,
    telefono character varying,
    email character varying,
    tipo character varying)
  RETURNS void AS
$BODY$
declare maximo integer;
begin 

select (max(id_usuario)+1) into maximo
  from usuario;

insert into usuario (id_usuario,nombre_usuario,contrasenia,nombre,apellido_paterno,apellido_materno,calle,avenida,numero,colonia,codigo_postal,ciudad,telefono,email,tipo)
values (maximo,nombre_usuario,contrasenia,nombre,apellido_paterno,apellido_materno,calle,avenida,numero,colonia,codigo_postal,ciudad,telefono,email,tipo);

end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;


/*CREATE OR REPLACE FUNCTION fn_eliminarcheckequipo(_idcheck_equipo integer)
  RETURNS void AS
$BODY$
begin 
update check_equipo set estatus = 'n' where id_check_equipo = _idcheck_equipo;
end
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION fn_eliminarcheckequipo(integer)
  OWNER TO postgres;*/

CREATE OR REPLACE FUNCTION fn_eliminarcliente(_idcliente integer)
  RETURNS void AS
$BODY$
begin 
update cliente set estatus = 'n' where id_cliente = _idcliente;
end
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION fn_eliminarcliente(integer)
  OWNER TO postgres;

CREATE OR REPLACE FUNCTION fn_eliminarusuario(_idusuario integer)
  RETURNS void AS
$BODY$
begin 
update usuario set estatus = 'n' where id_usuario = _idusuario;
end
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION fn_eliminarusuario(integer)
  OWNER TO postgres;



CREATE OR REPLACE FUNCTION fn_modificarcheckequipo(
    _idcheck_equipo integer,
    _tipo_equipo character varying,
    _sistema_equipo character varying,
    _sistema_operativo character varying,
    _falla_detectada character varying,
    _danios_presentados character varying,
    _diagnostico character varying)
  RETURNS void AS
$BODY$

begin 

Update check_equipo set id_check_equipo = _idcheck_equipo,tipo_equipo = _tipo_equipo,sistema_equipo = _sistema_equipo, sistema_operativo = _sistema_operativo,
falla_detectada = _falla_detectada,danios_presentados = _danios_presentados,diagnostico = _diagnostico where id_check_equipo = _idcheck_equipo;

end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
CREATE OR REPLACE FUNCTION fn_modificarcliente(
    _idcliente integer,
    _rfc character varying,
    _nombre character varying,
    _apellido_paterno character varying,
    _apellido_materno character varying,
    _calle character varying,
    _avenida character varying,
    _numero character varying,
    _colonia character varying,
    _codigo_postal integer,
    _ciudad character varying,
    _telefono character varying,
    _email character varying)
  RETURNS void AS
$BODY$

begin 

Update cliente set id_cliente = _idcliente,rfc = _rfc,nombre = _nombre, apellido_paterno = _apellido_paterno,apellido_materno = _apellido_materno,
calle = _calle,avenida = _avenida,numero = _numero,colonia = _colonia,codigo_postal = _codigo_postal,ciudad = _ciudad,telefono = _telefono,
email = _email where id_cliente = _idcliente;

end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
CREATE OR REPLACE FUNCTION fn_modificarusuario(
    _idusuario integer,
    _nombre_usuario character varying,
    _contrasenia character varying,
    _nombre character varying,
    _apellido_paterno character varying,
    _apellido_materno character varying,
    _calle character varying,
    _avenida character varying,
    _numero character varying,
    _colonia character varying,
    _codigo_postal integer,
    _ciudad character varying,
    _telefono character varying,
    _email character varying,
    _tipo character varying)
  RETURNS void AS
$BODY$

begin 

Update usuario set id_usuario = _idusuario,nombre_usuario = _nombre_usuario,contrasenia=_contrasenia,nombre = _nombre, apellido_paterno = _apellido_paterno,apellido_materno = _apellido_materno,
calle = _calle,avenida = _avenida,numero = _numero,colonia = _colonia,codigo_postal = _codigo_postal,ciudad = _ciudad,telefono = _telefono,
email = _email,tipo =_tipo where id_usuario = _idusuario;

end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;


CREATE OR REPLACE FUNCTION fnrestaurarcliente(_idcliente integer)
  RETURNS void AS
$BODY$
begin 
update cliente set estatus = 'n' where id_cliente = _idcliente;
end
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION fnrestaurarcliente(integer)
  OWNER TO postgres;
CREATE OR REPLACE FUNCTION fnrestaurarusuario(_idusuario integer)
  RETURNS void AS
$BODY$
begin 
update usuario set estatus = 'n' where id_usuario = _idusuario;
end
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION fnrestaurarusuario(integer)
  OWNER TO postgres;


/*CREATE OR REPLACE VIEW vwcheck AS 
 SELECT ch.id_check_equipo,
    ch.tipo_equipo,
    ch.sistema_equipo,
    ch.sistema_operativo,
    ch.falla_detectada,
    ch.danios_presentados,
    ch.diagnostico,
    ch.vendido AS vendido
   FROM check_equipo ch
  WHERE ch.vendido = 's'::bpchar
  ORDER BY ch.id_check_equipo;

ALTER TABLE vwcheck
  OWNER TO postgres;*/

CREATE OR REPLACE VIEW vwcliente AS 
 SELECT cl.id_cliente,
    cl.rfc,
    cl.nombre,
    cl.apellido_paterno,
    cl.apellido_materno,
    cl.calle,
    cl.avenida,
    cl.numero,
    cl.colonia,
    cl.codigo_postal,
    cl.ciudad,
    cl.telefono,
    cl.email,
    cl.estatus AS status
   FROM cliente cl
  WHERE cl.estatus = 's'::bpchar
  ORDER BY cl.id_cliente;

ALTER TABLE vwcliente
  OWNER TO postgres;
CREATE OR REPLACE VIEW vwusuario AS 
 SELECT u.id_usuario,
    u.nombre_usuario,
    u.contrasenia,
    u.nombre,
    u.apellido_paterno,
    u.apellido_materno,
    u.calle,
    u.avenida,
    u.numero,
    u.colonia,
    u.codigo_postal,
    u.ciudad,
    u.telefono,
    u.email,
    u.tipo,
    u.estatus AS status
   FROM usuario u
  WHERE u.estatus = 's'::bpchar
  ORDER BY u.id_usuario;

ALTER TABLE vwusuario
  OWNER TO postgres;

