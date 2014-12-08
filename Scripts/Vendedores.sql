
Insert into VENDEDOR (RUT,USUARIO,CONTRASE�A,TELEFONO,TIPO,CORREO,MEMBRESIA_CODIGO) values ('90000000-2','ciss','contrase�a','12341234','Inmobiliaria','ventas@ciss.cl','7');
Insert into VENDEDOR (RUT,USUARIO,CONTRASE�A,TELEFONO,TIPO,CORREO,MEMBRESIA_CODIGO) values ('18409893-8','fbaeza','contrase�a','96558462','Persona','baeza.gdo@gmail.com','3');
Insert into VENDEDOR (RUT,USUARIO,CONTRASE�A,TELEFONO,TIPO,CORREO,MEMBRESIA_CODIGO) values ('17742407-2','selgueta','contrase�a','99419525','Corredor','sandri.style@hotmail.com','2');

Insert into CORREDOR (VENDEDOR_RUT,NOMBRE,APELLIDOS,GENERO,EMPRESACORREDORA_RUT) values ('17742407-2','Sandra','Elgueta Brintrup','Femenino',null);

Insert into INMOBILIARIA (VENDEDOR_RUT,NOMBRE,PAGINAWEB,TIPO_SOCIEDAD,DOMICILIO_LEGAL,DOMICILIO_COMERCIAL) values ('90000000-2','CISS','www.ciss.cl',null,'concepcion',null);

Insert into PERSONA (VENDEDOR_RUT,NOMBRE,APELLIDOS,GENERO) values ('18409893-8','Francisco','Baeza Gallardo','Masculino');