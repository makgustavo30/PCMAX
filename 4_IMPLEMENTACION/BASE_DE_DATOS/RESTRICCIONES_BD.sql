ALTER TABLE categoria
ADD CONSTRAINT ck_categoria_mayor_a_cero
CHECK (id_categoria >0);

ALTER TABLE categoria
ADD CONSTRAINT ck_categoria_mas_3_caracteres_nombre
CHECK (length(ltrim(rtrim(nombre_categoria))) >= 3);


ALTER TABLE check_equipo
ADD CONSTRAINT ck_check_equipo_mayor_a_cero
CHECK (id_check_equipo >0);

ALTER TABLE cliente
ADD CONSTRAINT ck_cliente_mayor_a_cero
CHECK (id_cliente >0);

ALTER TABLE cliente
ADD CONSTRAINT ck_cliente_mas_3_caracteres_nombre
CHECK (length(ltrim(rtrim(nombre))) >= 3);

ALTER TABLE cliente
ADD CONSTRAINT ck_cliente_mas_3_caracteres_apellido_p
CHECK (length(ltrim(rtrim(apellido_paterno))) >= 3);

ALTER TABLE cliente
ADD CONSTRAINT ck_cliente_mas_3_caracteres_apellido_m
CHECK (length(ltrim(rtrim(apellido_materno))) >= 3);

ALTER TABLE cliente
ADD CONSTRAINT ck_cliente_mas_5_caracteres_colonia
CHECK (length(ltrim(rtrim(colonia))) >= 5);

alter table cliente
add constraint ck_cliente_email
check (email like '%@%');


ALTER TABLE cliente
ADD CONSTRAINT ck_cliente_ciudad
CHECK (ciudad in ('acajete', 'acatlán', 'acayucan', 'actopan', 'acula', 'acultzingo', 'agua dulce', 'alpatláhuac', 'alto lucero de gutiérrez', 'barrios', 
'altotonga', 'alvarado','amatitlán', 'amatlán de los reyes', 'angel r. cabada', 'apazapan', 'aquila', 'astacinga', 'atlahuilco', 'atoyac', 'atzacan','atzalan', 
'ayahualulco', 'banderilla', 'benito juárez', 'boca del río', 'calcahualco', 'camarón de tejeda', 'camerino z. mendoza', 'carlos a. carrillo', 'carrillo puerto', 
'castillo de teayo', 'catemaco', 'cazones', 'córdoba', 'cerro azul', 'chacaltianguis', 'chalma', 'chiconamel', 'chiconquiaco', 'chicontepec', 'chinameca', 
'chinampa de gorostiza', 'chocamán', 'chontla', 'chumatlán', 'citlaltépetl', 'coacoatzintla', 'coahuitlán', 'coatepec', 'coatzacoalcos', 'coatzintla', 'coetzala', 
'colipa', 'comapa', 'cosamaloapan de carpio', 'cosautlán de carvajal', 'coscomatepec', 'cosoleacaque', 'cotaxtla', 'coxquihui', 'coyutla', 'cuichapa', 'cuitláhuac', 
'el higo', 'emiliano zapata', 'espinal', 'filomeno mata', 'fortín', 'gutiérrez zamora', 'hidalgotitlán', 'huatusco', 'huayacocotla', 'hueyapan de ocampo', 'huiloapan', 
'ignacio de la llave', 'ilamatlán', 'isla', 'ixcatepec', 'ixhuacán de los reyes', 'ixhuatlancillo', 'ixhuatlán de madero', 'ixhuatlán del café', 'ixhuatlán del sureste', 
'ixmatlahuacan', 'ixtaczoquitlán', 'jalacingo', 'jalcomulco', 'jamapa', 'jáltipan', 'jesús carranza', 'jilotepec', 'josé azueta', 'juan rodríguez clara', 
'juchique de ferrer', 'la antigua', 'la perla', 'landero y coss', 'las choapas', 'las minas', 'las vigas de ramírez', 'lerdo de tejada', 'los reyes', 'magdalena', 
'maltrata', 'manlio fabio altamirano', 'mariano escobedo', 'martínez de la torre', 'mecatlán', 'mecayapan', 'medellín', 'miahuatlán', 'minatitlán', 'misantla', 
'mixtla de altamirano', 'moloacán', 'nanchital de lázaro cárdenas del río', 'naolinco', 'naranjal', 'naranjos amatlán', 'nautla', 'nogales', 'oluta', 'omealca', 
'orizaba', 'otatitlán', 'oteapan', 'ozuluama de mascareñas', 'pajapan', 'papantla', 'paso de ovejas', 'paso del macho', 'pánuco', 'perote', 'platón sánchez', 
'playa vicente', 'poza rica de hidalgo', 'pueblo viejo', 'puente nacional', 'rafael delgado', 'rafael lucio', 'río blanco', 'saltabarranca', 'san andrés tenejapan', 
'san andrés tuxtla', 'san juan evangelista', 'san rafael', 'santiago sochiapan', 'santiago tuxtla', 'sayula de alemán', 'sochiapa', 'soconusco', 'soledad atzompa', 
'soledad de doblado', 'soteapan', 'tamalín', 'tamiahua', 'tampico alto', 'tancoco', 'tantima', 'tantoyuca', 'tatahuicapan de juárez', 'tatatila', 'túxpam', 'tecolutla', 
'tehuipango', 'temapache', 'tempoal', 'tenampa', 'tenochtitlán', 'teocelo', 'tepatlaxco', 'tepetlán', 'tepetzintla', 'tequila', 'texcatepec', 'texhuacán', 'texistepec', 
'tezonapa', 'tierra blanca', 'tihuatlán', 'tlachichilco', 'tlacojalpan', 'tlacolulan', 'tlacotalpan', 'tlacotepec de mejía', 'tlalixcoyan', 'tlalnelhuayocan', 
'tlaltetela', 'tlapacoyan', 'tlaquilpa', 'tlilapan', 'tomatlán', 'tonayán', 'totutla', 'tres valles', 'tuxtilla', 'ursulo galván', 'uxpanapa', 'vega de alatorre', 
'veracruz', 'villa aldama', 'xalapa', 'xico', 'xoxocotla', 'yanga', 'yecuatla', 'zacualpan', 'zaragoza', 'zentla', 'zongolica', 'zontecomatlán de lópez y fuentes', 
'zozocolco de hidalgo'));


ALTER TABLE compra
ADD CONSTRAINT ck_id_compra_mayor_a_cero
CHECK (id_compra >0);

ALTER TABLE contacto_proveedor
ADD CONSTRAINT ck_id_contacto_mayor_a_cero
CHECK (id_contacto_proveedor >0);

ALTER TABLE contacto_proveedor
ADD CONSTRAINT ck_contacto_proveedor_mas_3_caracteres_nombre
CHECK (length(ltrim(rtrim(nombre))) >= 3);

ALTER TABLE contacto_proveedor
ADD CONSTRAINT ck_contacto_proveedor_mas_3_caracteres_apellido_p
CHECK (length(ltrim(rtrim(apellido_paterno))) >= 3);

ALTER TABLE contacto_proveedor
ADD CONSTRAINT ck_contacto_proveedor_mas_3_caracteres_apellido_m
CHECK (length(ltrim(rtrim(apellido_materno))) >= 3);

alter table contacto_proveedor
add constraint ck_contacto_proveedor_email
check (email like '%@%');






ALTER TABLE detalle_compra
ADD CONSTRAINT ck_detalle_compra_mayor_a_cero
CHECK (id_detalle_compra >0);

ALTER TABLE detalle_compra
ADD CONSTRAINT ck_detalle_cantidad_mayor_a_cero
CHECK (cantidad >= 0);

ALTER TABLE detalle_compra
ADD CONSTRAINT ck_detalle_compra_precio_no_negativo
CHECK (precio >= 0);

ALTER TABLE detalle_garantia
ADD CONSTRAINT ck_detalle_garantia_mayor_a_cero
CHECK (id_detalle_garantia >0);

ALTER TABLE detalle_garantia
ADD CONSTRAINT ck_cantidad_mayor_a_cero
CHECK (cantidad >= 0);

ALTER TABLE detalle_garantia
ADD CONSTRAINT ck_precio_no_negativo
CHECK (precio >= 0);

ALTER TABLE detalle_producto
ADD CONSTRAINT ck_detalle_producto_mayor_a_cero
CHECK (id_detalle_producto >0);

ALTER TABLE detalle_servicio
ADD CONSTRAINT ck_detalle_servicio_mayor_a_cero
CHECK (id_detalle_servicio >0);

ALTER TABLE detalle_servicio
ADD CONSTRAINT ck_servicio_cantidad_mayor_a_cero
CHECK (cantidad >= 0);

ALTER TABLE detalle_servicio
ADD CONSTRAINT ck_servicio_precio_no_negativo
CHECK (precio >= 0);

ALTER TABLE detalle_venta
ADD CONSTRAINT ck_detalle_venta_mayor_a_cero
CHECK (id_detalle_venta >0);

ALTER TABLE detalle_venta
ADD CONSTRAINT ck_detalle_venta_cantidad_mayor_a_cero
CHECK (cantidad >= 0);

ALTER TABLE detalle_venta
ADD CONSTRAINT ck_detalle_venta_precio_no_negativo
CHECK (precio >= 0);

ALTER TABLE producto
ADD CONSTRAINT ck_producto_mayor_a_cero
CHECK (id_producto >0);

ALTER TABLE producto
ADD CONSTRAINT ck_existencias_mayor_a_cero
CHECK (existencias >= 0);

ALTER TABLE producto
ADD CONSTRAINT ck_precio_compra_mayor_a_cero
CHECK (precio_de_compra >= 0);

ALTER TABLE producto
ADD CONSTRAINT ck_producto_precio_no_negativo_1
CHECK (precio_1 >= 0);

ALTER TABLE producto
ADD CONSTRAINT ck_producto_precio_no_negativo_2
CHECK (precio_2 >= 0);

ALTER TABLE producto
ADD CONSTRAINT ck_producto_precio_no_negativo_3
CHECK (precio_3 >= 0);

ALTER TABLE producto
ADD CONSTRAINT ck_producto_mayor_1
CHECK (precio_1 > precio_2);

ALTER TABLE producto
ADD CONSTRAINT ck_producto_mayor_2
CHECK (precio_2 > precio_3);



ALTER TABLE proveedor
ADD CONSTRAINT ck_proveedor_mayor_a_cero
CHECK (id_proveedor >0);

ALTER TABLE proveedor
ADD CONSTRAINT ck_proveedor_mas_3_caracteres_nombre
CHECK (length(ltrim(rtrim(nombre_empresa))) >= 3);

ALTER TABLE proveedor
ADD CONSTRAINT ck_proveedor_mas_5_caracteres_colonia
CHECK (length(ltrim(rtrim(colonia))) >= 5);

alter table proveedor
add constraint ck_proveedor_email
check (email like '%@%');



ALTER TABLE proveedor
ADD CONSTRAINT ck_proveedor_ciudad
CHECK (ciudad in ('acajete', 'acatlán', 'acayucan', 'actopan', 'acula', 'acultzingo', 'agua dulce', 'alpatláhuac', 'alto lucero de gutiérrez', 'barrios', 
'altotonga', 'alvarado','amatitlán', 'amatlán de los reyes', 'angel r. cabada', 'apazapan', 'aquila', 'astacinga', 'atlahuilco', 'atoyac', 'atzacan','atzalan', 
'ayahualulco', 'banderilla', 'benito juárez', 'boca del río', 'calcahualco', 'camarón de tejeda', 'camerino z. mendoza', 'carlos a. carrillo', 'carrillo puerto', 
'castillo de teayo', 'catemaco', 'cazones', 'córdoba', 'cerro azul', 'chacaltianguis', 'chalma', 'chiconamel', 'chiconquiaco', 'chicontepec', 'chinameca', 
'chinampa de gorostiza', 'chocamán', 'chontla', 'chumatlán', 'citlaltépetl', 'coacoatzintla', 'coahuitlán', 'coatepec', 'coatzacoalcos', 'coatzintla', 'coetzala', 
'colipa', 'comapa', 'cosamaloapan de carpio', 'cosautlán de carvajal', 'coscomatepec', 'cosoleacaque', 'cotaxtla', 'coxquihui', 'coyutla', 'cuichapa', 'cuitláhuac', 
'el higo', 'emiliano zapata', 'espinal', 'filomeno mata', 'fortín', 'gutiérrez zamora', 'hidalgotitlán', 'huatusco', 'huayacocotla', 'hueyapan de ocampo', 'huiloapan', 
'ignacio de la llave', 'ilamatlán', 'isla', 'ixcatepec', 'ixhuacán de los reyes', 'ixhuatlancillo', 'ixhuatlán de madero', 'ixhuatlán del café', 'ixhuatlán del sureste', 
'ixmatlahuacan', 'ixtaczoquitlán', 'jalacingo', 'jalcomulco', 'jamapa', 'jáltipan', 'jesús carranza', 'jilotepec', 'josé azueta', 'juan rodríguez clara', 
'juchique de ferrer', 'la antigua', 'la perla', 'landero y coss', 'las choapas', 'las minas', 'las vigas de ramírez', 'lerdo de tejada', 'los reyes', 'magdalena', 
'maltrata', 'manlio fabio altamirano', 'mariano escobedo', 'martínez de la torre', 'mecatlán', 'mecayapan', 'medellín', 'miahuatlán', 'minatitlán', 'misantla', 
'mixtla de altamirano', 'moloacán', 'nanchital de lázaro cárdenas del río', 'naolinco', 'naranjal', 'naranjos amatlán', 'nautla', 'nogales', 'oluta', 'omealca', 
'orizaba', 'otatitlán', 'oteapan', 'ozuluama de mascareñas', 'pajapan', 'papantla', 'paso de ovejas', 'paso del macho', 'pánuco', 'perote', 'platón sánchez', 
'playa vicente', 'poza rica de hidalgo', 'pueblo viejo', 'puente nacional', 'rafael delgado', 'rafael lucio', 'río blanco', 'saltabarranca', 'san andrés tenejapan', 
'san andrés tuxtla', 'san juan evangelista', 'san rafael', 'santiago sochiapan', 'santiago tuxtla', 'sayula de alemán', 'sochiapa', 'soconusco', 'soledad atzompa', 
'soledad de doblado', 'soteapan', 'tamalín', 'tamiahua', 'tampico alto', 'tancoco', 'tantima', 'tantoyuca', 'tatahuicapan de juárez', 'tatatila', 'túxpam', 'tecolutla', 
'tehuipango', 'temapache', 'tempoal', 'tenampa', 'tenochtitlán', 'teocelo', 'tepatlaxco', 'tepetlán', 'tepetzintla', 'tequila', 'texcatepec', 'texhuacán', 'texistepec', 
'tezonapa', 'tierra blanca', 'tihuatlán', 'tlachichilco', 'tlacojalpan', 'tlacolulan', 'tlacotalpan', 'tlacotepec de mejía', 'tlalixcoyan', 'tlalnelhuayocan', 
'tlaltetela', 'tlapacoyan', 'tlaquilpa', 'tlilapan', 'tomatlán', 'tonayán', 'totutla', 'tres valles', 'tuxtilla', 'ursulo galván', 'uxpanapa', 'vega de alatorre', 
'veracruz', 'villa aldama', 'xalapa', 'xico', 'xoxocotla', 'yanga', 'yecuatla', 'zacualpan', 'zaragoza', 'zentla', 'zongolica', 'zontecomatlán de lópez y fuentes', 
'zozocolco de hidalgo'));

ALTER TABLE servicio
ADD CONSTRAINT ck_servicio_mayor_a_cero
CHECK (id_servicio >0);

ALTER TABLE servicio
ADD CONSTRAINT ck_servicio_precio_no_negativo_1
CHECK (precio_1 >= 0);

ALTER TABLE servicio
ADD CONSTRAINT ck_servicio_precio_no_negativo_2
CHECK (precio_2 >= 0);

ALTER TABLE servicio
ADD CONSTRAINT ck_servicio_precio_no_negativo_3
CHECK (precio_3 >= 0);

ALTER TABLE servicio
ADD CONSTRAINT ck_servicio_mayor_1
CHECK (precio_1 > precio_2);

ALTER TABLE servicio
ADD CONSTRAINT ck_servicio_mayor_2
CHECK (precio_2 > precio_3);



ALTER TABLE usuario
ADD CONSTRAINT ck_usuario_mayor_a_cero
CHECK (id_usuario >0);

ALTER TABLE usuario
ADD CONSTRAINT ck_usuario_mas_3_caracteres_nombre
CHECK (length(ltrim(rtrim(nombre))) >= 3);

ALTER TABLE usuario
ADD CONSTRAINT ck_usuario_mas_3_caracteres_apellido_p
CHECK (length(ltrim(rtrim(apellido_paterno))) >= 3);

ALTER TABLE usuario
ADD CONSTRAINT ck_usuario_mas_3_caracteres_apellido_m
CHECK (length(ltrim(rtrim(apellido_materno))) >= 3);

ALTER TABLE usuario
ADD CONSTRAINT ck_usuario_mas_5_caracteres_colonia
CHECK (length(ltrim(rtrim(colonia))) >= 5);

alter table usuario
add constraint ck_usuario_email
check (email like '%@%');



ALTER TABLE usuario
ADD CONSTRAINT ck_usuario_ciudad
CHECK (ciudad in ('acajete', 'acatlán', 'acayucan', 'actopan', 'acula', 'acultzingo', 'agua dulce', 'alpatláhuac', 'alto lucero de gutiérrez', 'barrios', 
'altotonga', 'alvarado','amatitlán', 'amatlán de los reyes', 'angel r. cabada', 'apazapan', 'aquila', 'astacinga', 'atlahuilco', 'atoyac', 'atzacan','atzalan', 
'ayahualulco', 'banderilla', 'benito juárez', 'boca del río', 'calcahualco', 'camarón de tejeda', 'camerino z. mendoza', 'carlos a. carrillo', 'carrillo puerto', 
'castillo de teayo', 'catemaco', 'cazones', 'córdoba', 'cerro azul', 'chacaltianguis', 'chalma', 'chiconamel', 'chiconquiaco', 'chicontepec', 'chinameca', 
'chinampa de gorostiza', 'chocamán', 'chontla', 'chumatlán', 'citlaltépetl', 'coacoatzintla', 'coahuitlán', 'coatepec', 'coatzacoalcos', 'coatzintla', 'coetzala', 
'colipa', 'comapa', 'cosamaloapan de carpio', 'cosautlán de carvajal', 'coscomatepec', 'cosoleacaque', 'cotaxtla', 'coxquihui', 'coyutla', 'cuichapa', 'cuitláhuac', 
'el higo', 'emiliano zapata', 'espinal', 'filomeno mata', 'fortín', 'gutiérrez zamora', 'hidalgotitlán', 'huatusco', 'huayacocotla', 'hueyapan de ocampo', 'huiloapan', 
'ignacio de la llave', 'ilamatlán', 'isla', 'ixcatepec', 'ixhuacán de los reyes', 'ixhuatlancillo', 'ixhuatlán de madero', 'ixhuatlán del café', 'ixhuatlán del sureste', 
'ixmatlahuacan', 'ixtaczoquitlán', 'jalacingo', 'jalcomulco', 'jamapa', 'jáltipan', 'jesús carranza', 'jilotepec', 'josé azueta', 'juan rodríguez clara', 
'juchique de ferrer', 'la antigua', 'la perla', 'landero y coss', 'las choapas', 'las minas', 'las vigas de ramírez', 'lerdo de tejada', 'los reyes', 'magdalena', 
'maltrata', 'manlio fabio altamirano', 'mariano escobedo', 'martínez de la torre', 'mecatlán', 'mecayapan', 'medellín', 'miahuatlán', 'minatitlán', 'misantla', 
'mixtla de altamirano', 'moloacán', 'nanchital de lázaro cárdenas del río', 'naolinco', 'naranjal', 'naranjos amatlán', 'nautla', 'nogales', 'oluta', 'omealca', 
'orizaba', 'otatitlán', 'oteapan', 'ozuluama de mascareñas', 'pajapan', 'papantla', 'paso de ovejas', 'paso del macho', 'pánuco', 'perote', 'platón sánchez', 
'playa vicente', 'poza rica de hidalgo', 'pueblo viejo', 'puente nacional', 'rafael delgado', 'rafael lucio', 'río blanco', 'saltabarranca', 'san andrés tenejapan', 
'san andrés tuxtla', 'san juan evangelista', 'san rafael', 'santiago sochiapan', 'santiago tuxtla', 'sayula de alemán', 'sochiapa', 'soconusco', 'soledad atzompa', 
'soledad de doblado', 'soteapan', 'tamalín', 'tamiahua', 'tampico alto', 'tancoco', 'tantima', 'tantoyuca', 'tatahuicapan de juárez', 'tatatila', 'túxpam', 'tecolutla', 
'tehuipango', 'temapache', 'tempoal', 'tenampa', 'tenochtitlán', 'teocelo', 'tepatlaxco', 'tepetlán', 'tepetzintla', 'tequila', 'texcatepec', 'texhuacán', 'texistepec', 
'tezonapa', 'tierra blanca', 'tihuatlán', 'tlachichilco', 'tlacojalpan', 'tlacolulan', 'tlacotalpan', 'tlacotepec de mejía', 'tlalixcoyan', 'tlalnelhuayocan', 
'tlaltetela', 'tlapacoyan', 'tlaquilpa', 'tlilapan', 'tomatlán', 'tonayán', 'totutla', 'tres valles', 'tuxtilla', 'ursulo galván', 'uxpanapa', 'vega de alatorre', 
'veracruz', 'villa aldama', 'xalapa', 'xico', 'xoxocotla', 'yanga', 'yecuatla', 'zacualpan', 'zaragoza', 'zentla', 'zongolica', 'zontecomatlán de lópez y fuentes', 
'zozocolco de hidalgo'));

ALTER TABLE usuario
ADD CONSTRAINT ck_nom_usuario_mas_3_caracteres_nombre
CHECK (length(ltrim(rtrim(nombre_usuario))) >= 3);

ALTER TABLE usuario
ADD CONSTRAINT ck_contrasenia_mas_3_caracteres_nombre
CHECK (length(ltrim(rtrim(contrasenia))) >= 4);

ALTER TABLE venta
ADD CONSTRAINT ck_venta_mayor_a_cero
CHECK (id_venta >0);