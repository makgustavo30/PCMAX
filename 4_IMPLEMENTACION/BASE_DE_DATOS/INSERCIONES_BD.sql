INSERT INTO usuario ("id_usuario","nombre","apellido_paterno","apellido_materno","calle","avenida","numero","colonia","codigo_postal","ciudad","telefono","email","nombre_usuario","contrasenia","tipo","estatus")
VALUES (1, 'Gustavo', 'Orbezo','Hernandez','Calle 17 y 19','Avenida 12','1732','Las Flores',94620,'córdoba','2711528628','maki_rule@hotmail.com','gustavo','maki3001', 'admin',DEFAULT),
(2, 'Elizabeth', 'Ramirez','Medina','Calle 1','Avenida 3','103','Centro',9450,'córdoba','2711235698','eli@hotmail.com','eli','michel', 'usuario',DEFAULT),
(3, 'Alejandro', 'Martinez','Valdes','Calle 30','Avenida 13','1325','La Sidra',94640,'córdoba','2711635648','ale@hotmail.com','ale','ale30', 'admin',DEFAULT),
(4, 'Juan', 'Bautista','Lopez','Calle 3','Avenida 1','101','Centro',94500,'córdoba','2711896543','juan@hotmail.com','juan','juan24', 'usuario',DEFAULT);

insert into categoria (id_categoria, nombre_categoria)
values (1,'memorias'),
(2,'teclados'),
(3,'mouse'),
(4,'displays'),
(5,'tarjeta madre'),
(6,'impresoras'),
(7,'tarjeta de red');

insert into marcas (id_marca,nombre_marca)
values (1,'acer'),
(2,'adata'),
(3,'kingston'),
(4,'samsung'),
(5,'genius'),
(6,'hp'),
(7,'asus');



insert into producto (id_producto,id_categoria,id_marca,numero_de_parte,existencias,precio_de_compra,
precio_1,precio_2,precio_3,descripcion,stock_maximo,stock_minimo)
values (1,1,5,'eg457df',10,80.00,120.00,110.00,90.00,'teclado inalambrico',20,2),
(2,2,5,'mipc3435',20,60.00,100.00,90.00,80.00,'mouse inalambrico',20,2),
(3,3,6,'lcdmt15pde345',5,500.00,800.00,700.00,600.00,'monitor lcd 15 pulgadas',20,2),
(4,4,4,'ddrsk5334jfo3',2,800.00,1200.00,1100.00,1000.00,'disco duro 1tb',20,2),
(5,5,3,'kfomco3950kdd',15,400.00,550.00,500.00,480.00,'memoria ddr2 1gb',20,2),
(6,6,6,'edkjgfkads3434',10,2000.00,1900.00,1850.00,1700.00,'multifuncional HP',20,2),
(7,7,2,'jgod8934hd',6,120.00,150.00,145.00,140.00,'Tarjeta de red 300mbp',20,2),
(8,1,4,'hf7sk94nd',8,40.00,100.00,90.00,80.00,'teclado ps2',20,2),
(9,2,4,'a06fls530fne74',1,20.00,40.00,30.00,25.00,'mouse ps2',20,2),
(10,3,7,'ytbv89375nvp84',11,1200.00,2000.00,1900.00,1850.00,'monitor led 18 pulgadas',20,2),
(11,4,6,'hf8730fnds9e',12,600.00,800.00,750.00,700.00,'disco duro 750gb',20,2),
(12,5,3,'8fs83jd03dsasd',15,500.00,800.00,750.00,700.00,'memoria ddr3 4gb',20,2),
(13,6,7,'afhdu48493j83jd83',10,1500.00,2200.00,2100.00,2000.00,'multifucnional tinta continua',20,2),
(14,7,3,'hf74nd84jfo3',10,60.00,120.00,110.00,100.00,'tarjeta de red para laptop hp dv6000',20,2);



insert into detalle_producto (id_detalle_producto,numero_serie,vendido,id_producto)
values (1,'BDAEV0Q5Y4H3KT',B'1',1),
(2,'BDAEV0Q4Y4H3KT',B'1',2),
(3,'BDAEV0E6Y4H3KT',B'1',3),
(4,'BDAEV0F5Y4H3KT',B'1',4),
(5,'BDAEV0G5Y4H3KT',B'1',5),
(6,'BDAEV0H5Y4H3KT',B'1',6),
(7,'BDAEV0I5Y4H3KT',B'1',7),
(8,'BDAEV0J5Y4H3KT',B'1',8),
(9,'BDAEV0K5Y4H3KT',B'1',9),
(10,'BDAEVDQ5Y4H3KT',B'1',10),
(11,'BDAEVEQ5Y4H3KT',B'1',11),
(12,'BDAEVRQ5Y4H3KT',B'1',12),
(13,'BDAEVFQ5Y4H3KT',B'1',13),
(14,'BDAEVSQ5Y4H3KT',B'1',14);



insert into cliente (id_cliente,rfc,nombre,apellido_paterno,apellido_materno,calle,avenida,numero,colonia,codigo_postal,ciudad,telefono,email)
values (1,'ramael259634gd','publico','e n','general','','de la libertad','s/n','las flores',93560,'cuichapa','2711592627','eliz@hotmail.com'),
(2,'oehg300185345','gustavo','orbezo','hernandez','calle 17 y 19','avenida 12','1732','las flores',94620,'córdoba','2711528628','maki_rule@hotmail.com'),
(3,'maba880904asd','alejandro','martinez','valdes','calle 1','avenida 8','108','centro',94500,'córdoba','2711586628','alemartinez@gmail.com'),
(4,'ramael259634sd','elizabeth','ramirez','medina','s/n','de la libertad','s/n','las flores',93560,'cuichapa','2711592628','elizz@hotmail.com');


insert into check_equipo (id_check_equipo,tipo_equipo,sistema_equipo,sistema_operativo,danios_presentados,falla_detectada,diagnostico)
values (1,'n/a','n/a','n/a','n/a','n/a','n/a');