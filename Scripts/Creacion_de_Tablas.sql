-- Generado por Oracle SQL Developer Data Modeler 3.1.4.710
--   en:        2014-12-10 00:59:29 ART
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



CREATE TABLE Consulta 
    ( 
     codigo NUMBER  NOT NULL , 
     nombre VARCHAR2 (360)  NOT NULL , 
     hora TIMESTAMP WITH LOCAL TIME ZONE , 
     mensaje VARCHAR2 (360)  NOT NULL , 
     correo_respuesta VARCHAR2 (360)  NOT NULL , 
     articulo_codigo NUMBER  NOT NULL 
    ) 
;



ALTER TABLE Consulta 
    ADD CONSTRAINT Consulta_PK PRIMARY KEY ( codigo ) ;



CREATE TABLE Fotos 
    ( 
     id NUMBER  NOT NULL , 
     articulo_codigo NUMBER  NOT NULL , 
     PHOTOS_IMAGEID NUMBER  NOT NULL 
    ) 
;



ALTER TABLE Fotos 
    ADD CONSTRAINT Fotos_PK PRIMARY KEY ( id ) ;



CREATE TABLE Membresia 
    ( 
     codigo NUMBER  NOT NULL , 
     nombre VARCHAR2 (360)  NOT NULL , 
     nfotos NUMBER  NOT NULL , 
     precio NUMBER  NOT NULL , 
     ndestacados NUMBER  NOT NULL , 
     npublicaciones NUMBER  NOT NULL , 
     tipo VARCHAR2 (20)  NOT NULL CHECK ( tipo IN ('Corredor', 'Inmobiliaria', 'Persona')) 
    ) 
;



ALTER TABLE Membresia 
    ADD CONSTRAINT Membresia_PK PRIMARY KEY ( codigo ) ;



CREATE TABLE PHOTOS 
    ( 
     IMAGEID NUMBER  NOT NULL , 
     IMAGE BLOB  NOT NULL , 
     TYPE VARCHAR2 (5)  NOT NULL 
    ) 
    LOGGING 
;



ALTER TABLE PHOTOS 
    ADD CONSTRAINT PHOTOS_PK PRIMARY KEY ( IMAGEID ) ;



CREATE TABLE Vendedor 
    ( 
     rut VARCHAR2 (30)  NOT NULL , 
     usuario VARCHAR2 (360)  NOT NULL , 
     contrase�a VARCHAR2 (360)  NOT NULL , 
     telefono VARCHAR2 (360) , 
     tipo VARCHAR2 (20)  NOT NULL CHECK ( tipo IN ('Corredor', 'Inmobiliaria', 'Persona')) , 
     correo VARCHAR2 (360)  NOT NULL , 
     Membresia_codigo NUMBER  NOT NULL , 
     calificacion FLOAT DEFAULT 0 
    ) 
;



ALTER TABLE Vendedor 
    ADD CONSTRAINT Vendedor_PK PRIMARY KEY ( rut ) ;



CREATE TABLE articulo 
    ( 
     codigo NUMBER  NOT NULL , 
     titulo VARCHAR2 (360)  NOT NULL , 
     descripcion VARCHAR2 (360)  NOT NULL , 
     monto VARCHAR2 (360)  NOT NULL , 
     direccion VARCHAR2 (360)  NOT NULL , 
     codi_interno VARCHAR2 (360) , 
     fecha TIMESTAMP WITH LOCAL TIME ZONE , 
     superficie VARCHAR2 (360) , 
     destacada CHAR (1) DEFAULT '0' CHECK ( destacada IN ('0', '1')) , 
     estructura VARCHAR2 (100)  NOT NULL CHECK ( estructura IN ('Casa', 'Departamento', 'Terreno')) , 
     tipo VARCHAR2 (10)  NOT NULL CHECK ( tipo IN ('Arriendo', 'Venta')) , 
     piezas NUMBER DEFAULT 0 , 
     superficieconstruida NUMBER DEFAULT 0 , 
     ba�os NUMBER DEFAULT 0 , 
     pisos NUMBER DEFAULT 0 , 
     ciudad VARCHAR2 (360) , 
     calificacion FLOAT DEFAULT 0 , 
     Vendedor_rut VARCHAR2 (30)  NOT NULL , 
     comuna_codigo NUMBER  NOT NULL 
    ) 
;



ALTER TABLE articulo 
    ADD CONSTRAINT articulo_PK PRIMARY KEY ( codigo ) ;



CREATE TABLE calificacion 
    ( 
     articulo_codigo NUMBER  NOT NULL , 
     Vendedor_rut VARCHAR2 (30)  NOT NULL , 
     calificacion FLOAT  NOT NULL CHECK ( calificacion BETWEEN 0 AND 5) 
    ) 
;



ALTER TABLE calificacion 
    ADD CONSTRAINT calificacion_PK PRIMARY KEY ( articulo_codigo, Vendedor_rut ) ;



CREATE TABLE calificacionv 
    ( 
     Vendedor_rut VARCHAR2 (30)  NOT NULL , 
     Vendedor_rut1 VARCHAR2 (30)  NOT NULL , 
     calificacion FLOAT  NOT NULL 
    ) 
;



ALTER TABLE calificacionv 
    ADD CONSTRAINT calificacionv_PK PRIMARY KEY ( Vendedor_rut, Vendedor_rut1 ) ;



CREATE TABLE comentario 
    ( 
     codigo NUMBER  NOT NULL , 
     fecha TIMESTAMP WITH LOCAL TIME ZONE  NOT NULL , 
     mensaje VARCHAR2 (360)  NOT NULL , 
     articulo_codigo NUMBER  NOT NULL , 
     Vendedor_rut VARCHAR2 (30)  NOT NULL 
    ) 
;



ALTER TABLE comentario 
    ADD CONSTRAINT comentario_PK PRIMARY KEY ( codigo ) ;



CREATE TABLE comuna 
    ( 
     codigo NUMBER  NOT NULL , 
     nombre VARCHAR2 (360)  NOT NULL , 
     provincia_codigo NUMBER  NOT NULL 
    ) 
;



ALTER TABLE comuna 
    ADD CONSTRAINT comuna_PK PRIMARY KEY ( codigo ) ;



CREATE TABLE conectado 
    ( 
     Vendedor_rut VARCHAR2 (30)  NOT NULL , 
     ip VARCHAR2 (15) , 
     horainicio TIMESTAMP WITH LOCAL TIME ZONE 
    ) 
;



ALTER TABLE conectado 
    ADD CONSTRAINT conectado_PK PRIMARY KEY ( Vendedor_rut ) ;



CREATE TABLE corredor 
    ( 
     Vendedor_rut VARCHAR2 (30)  NOT NULL , 
     nombre VARCHAR2 (360)  NOT NULL , 
     apellidos VARCHAR2 (360)  NOT NULL , 
     genero VARCHAR2 (10)  NOT NULL CHECK ( genero IN ('Femenino', 'Masculino')) , 
     empresacorredora_rut VARCHAR2 (360) 
    ) 
;



ALTER TABLE corredor 
    ADD CONSTRAINT corredor_PK PRIMARY KEY ( Vendedor_rut ) ;



CREATE TABLE empresacorredora 
    ( 
     rut VARCHAR2 (360)  NOT NULL , 
     nombre VARCHAR2 (360)  NOT NULL , 
     paginaweb VARCHAR2 (360) DEFAULT 'Sin Definir' , 
     correo VARCHAR2 (360) DEFAULT 'Sin Definir' , 
     PHOTOS_IMAGEID NUMBER 
    ) 
;



ALTER TABLE empresacorredora 
    ADD CONSTRAINT empresacorredora_PK PRIMARY KEY ( rut ) ;



CREATE TABLE inmobiliaria 
    ( 
     Vendedor_rut VARCHAR2 (30)  NOT NULL , 
     nombre VARCHAR2 (360)  NOT NULL , 
     paginaweb VARCHAR2 (360) DEFAULT 'Sin Definir' , 
     tipo_sociedad VARCHAR2 (360) DEFAULT 'Sin Definir' , 
     domicilio_legal VARCHAR2 (360) DEFAULT 'Sin Definir' , 
     domicilio_comercial VARCHAR2 (360) DEFAULT 'Sin Definir' , 
     PHOTOS_IMAGEID NUMBER 
    ) 
;



ALTER TABLE inmobiliaria 
    ADD CONSTRAINT inmobiliaria_PK PRIMARY KEY ( Vendedor_rut ) ;



CREATE TABLE persona 
    ( 
     Vendedor_rut VARCHAR2 (30)  NOT NULL , 
     nombre VARCHAR2 (360)  NOT NULL , 
     apellidos VARCHAR2 (360)  NOT NULL , 
     genero VARCHAR2 (10)  NOT NULL CHECK ( genero IN ('Femenino', 'Masculino')) 
    ) 
;



ALTER TABLE persona 
    ADD CONSTRAINT persona_PK PRIMARY KEY ( Vendedor_rut ) ;



CREATE TABLE provincia 
    ( 
     codigo NUMBER  NOT NULL , 
     nombre VARCHAR2 (360)  NOT NULL , 
     region_codigo NUMBER  NOT NULL 
    ) 
;



ALTER TABLE provincia 
    ADD CONSTRAINT provincia_PK PRIMARY KEY ( codigo ) ;



CREATE TABLE region 
    ( 
     codigo NUMBER  NOT NULL , 
     nombre VARCHAR2 (360)  NOT NULL , 
     posicion NUMBER 
    ) 
;



ALTER TABLE region 
    ADD CONSTRAINT region_PK PRIMARY KEY ( codigo ) ;




ALTER TABLE Consulta 
    ADD CONSTRAINT Consulta_articulo_FK FOREIGN KEY 
    ( 
     articulo_codigo
    ) 
    REFERENCES articulo 
    ( 
     codigo
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE Fotos 
    ADD CONSTRAINT Fotos_PHOTOS_FK FOREIGN KEY 
    ( 
     PHOTOS_IMAGEID
    ) 
    REFERENCES PHOTOS 
    ( 
     IMAGEID
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE Fotos 
    ADD CONSTRAINT Fotos_articulo_FK FOREIGN KEY 
    ( 
     articulo_codigo
    ) 
    REFERENCES articulo 
    ( 
     codigo
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE Vendedor 
    ADD CONSTRAINT Vendedor_Membresia_FK FOREIGN KEY 
    ( 
     Membresia_codigo
    ) 
    REFERENCES Membresia 
    ( 
     codigo
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE articulo 
    ADD CONSTRAINT articulo_Vendedor_FK FOREIGN KEY 
    ( 
     Vendedor_rut
    ) 
    REFERENCES Vendedor 
    ( 
     rut
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE articulo 
    ADD CONSTRAINT articulo_comuna_FK FOREIGN KEY 
    ( 
     comuna_codigo
    ) 
    REFERENCES comuna 
    ( 
     codigo
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE calificacion 
    ADD CONSTRAINT calificacion_Vendedor_FK FOREIGN KEY 
    ( 
     Vendedor_rut
    ) 
    REFERENCES Vendedor 
    ( 
     rut
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE calificacion 
    ADD CONSTRAINT calificacion_articulo_FK FOREIGN KEY 
    ( 
     articulo_codigo
    ) 
    REFERENCES articulo 
    ( 
     codigo
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE calificacionv 
    ADD CONSTRAINT calificacionv_Vendedor_FK FOREIGN KEY 
    ( 
     Vendedor_rut
    ) 
    REFERENCES Vendedor 
    ( 
     rut
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE calificacionv 
    ADD CONSTRAINT calificacionv_Vendedor_FKv1 FOREIGN KEY 
    ( 
     Vendedor_rut1
    ) 
    REFERENCES Vendedor 
    ( 
     rut
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE comentario 
    ADD CONSTRAINT comentario_Vendedor_FK FOREIGN KEY 
    ( 
     Vendedor_rut
    ) 
    REFERENCES Vendedor 
    ( 
     rut
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE comentario 
    ADD CONSTRAINT comentario_articulo_FK FOREIGN KEY 
    ( 
     articulo_codigo
    ) 
    REFERENCES articulo 
    ( 
     codigo
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE comuna 
    ADD CONSTRAINT comuna_provincia_FK FOREIGN KEY 
    ( 
     provincia_codigo
    ) 
    REFERENCES provincia 
    ( 
     codigo
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE conectado 
    ADD CONSTRAINT conectado_Vendedor_FK FOREIGN KEY 
    ( 
     Vendedor_rut
    ) 
    REFERENCES Vendedor 
    ( 
     rut
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE corredor 
    ADD CONSTRAINT corredor_Vendedor_FK FOREIGN KEY 
    ( 
     Vendedor_rut
    ) 
    REFERENCES Vendedor 
    ( 
     rut
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE corredor 
    ADD CONSTRAINT corredor_empresacorredora_FK FOREIGN KEY 
    ( 
     empresacorredora_rut
    ) 
    REFERENCES empresacorredora 
    ( 
     rut
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE empresacorredora 
    ADD CONSTRAINT empresacorredora_PHOTOS_FK FOREIGN KEY 
    ( 
     PHOTOS_IMAGEID
    ) 
    REFERENCES PHOTOS 
    ( 
     IMAGEID
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE inmobiliaria 
    ADD CONSTRAINT inmobiliaria_PHOTOS_FK FOREIGN KEY 
    ( 
     PHOTOS_IMAGEID
    ) 
    REFERENCES PHOTOS 
    ( 
     IMAGEID
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE inmobiliaria 
    ADD CONSTRAINT inmobiliaria_Vendedor_FK FOREIGN KEY 
    ( 
     Vendedor_rut
    ) 
    REFERENCES Vendedor 
    ( 
     rut
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE persona 
    ADD CONSTRAINT persona_Vendedor_FK FOREIGN KEY 
    ( 
     Vendedor_rut
    ) 
    REFERENCES Vendedor 
    ( 
     rut
    ) 
    NOT DEFERRABLE 
;


ALTER TABLE provincia 
    ADD CONSTRAINT provincia_region_FK FOREIGN KEY 
    ( 
     region_codigo
    ) 
    REFERENCES region 
    ( 
     codigo
    ) 
    NOT DEFERRABLE 
;

CREATE SEQUENCE Consulta_codigo_SEQ 
    NOCACHE 
    ORDER 
;

CREATE SEQUENCE Fotos_id_SEQ 
    NOCACHE 
    ORDER 
;

CREATE SEQUENCE articulo_codigo_SEQ 
    NOCACHE 
    ORDER 
;

CREATE SEQUENCE comentario_codigo_SEQ 
    NOCACHE 
    ORDER 
;

CREATE SEQUENCE comuna_codigo_SEQ 
    NOCACHE 
    ORDER 
;

CREATE SEQUENCE provincia_codigo_SEQ 
    NOCACHE 
    ORDER 
;

CREATE SEQUENCE region_codigo_SEQ 
    NOCACHE 
    ORDER 
;

CREATE OR REPLACE TRIGGER Consulta_codigo_TRG 
    BEFORE INSERT ON Consulta 
    FOR EACH ROW 
    WHEN ( NEW.codigo IS NULL ) 
BEGIN 
    SELECT Consulta_codigo_SEQ.NEXTVAL INTO :NEW.codigo FROM DUAL; 
END; 
/


CREATE OR REPLACE TRIGGER Fotos_id_TRG 
    BEFORE INSERT ON Fotos 
    FOR EACH ROW 
    WHEN ( NEW.id IS NULL ) 
BEGIN 
    SELECT Fotos_id_SEQ.NEXTVAL INTO :NEW.id FROM DUAL; 
END; 
/


CREATE OR REPLACE TRIGGER articulo_codigo_TRG 
    BEFORE INSERT ON articulo 
    FOR EACH ROW 
    WHEN ( NEW.codigo IS NULL ) 
BEGIN 
    SELECT articulo_codigo_SEQ.NEXTVAL INTO :NEW.codigo FROM DUAL; 
END; 
/


CREATE OR REPLACE TRIGGER comentario_codigo_TRG 
    BEFORE INSERT ON comentario 
    FOR EACH ROW 
    WHEN ( NEW.codigo IS NULL ) 
BEGIN 
    SELECT comentario_codigo_SEQ.NEXTVAL INTO :NEW.codigo FROM DUAL; 
END; 
/


CREATE OR REPLACE TRIGGER comuna_codigo_TRG 
    BEFORE INSERT ON comuna 
    FOR EACH ROW 
    WHEN ( NEW.codigo IS NULL ) 
BEGIN 
    SELECT comuna_codigo_SEQ.NEXTVAL INTO :NEW.codigo FROM DUAL; 
END; 
/


CREATE OR REPLACE TRIGGER provincia_codigo_TRG 
    BEFORE INSERT ON provincia 
    FOR EACH ROW 
    WHEN ( NEW.codigo IS NULL ) 
BEGIN 
    SELECT provincia_codigo_SEQ.NEXTVAL INTO :NEW.codigo FROM DUAL; 
END; 
/


CREATE OR REPLACE TRIGGER region_codigo_TRG 
    BEFORE INSERT ON region 
    FOR EACH ROW 
    WHEN ( NEW.codigo IS NULL ) 
BEGIN 
    SELECT region_codigo_SEQ.NEXTVAL INTO :NEW.codigo FROM DUAL; 
END; 
/


CREATE SEQUENCE PHOTOS_IMAGEID_SEQ 
    NOCACHE 
    ORDER ;

CREATE OR REPLACE TRIGGER PHOTOS_IMAGEID_TRG 
BEFORE INSERT ON PHOTOS 
FOR EACH ROW 
WHEN (NEW.IMAGEID IS NULL) 
BEGIN 
    SELECT PHOTOS_IMAGEID_SEQ.NEXTVAL INTO :NEW.IMAGEID FROM DUAL; 
END;
/



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            17
-- CREATE INDEX                             0
-- ALTER TABLE                             38
-- CREATE VIEW                              0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           8
-- ALTER TRIGGER                            0
-- CREATE STRUCTURED TYPE                   0
-- CREATE COLLECTION TYPE                   0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          8
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
