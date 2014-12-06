--------------------------------------------------------
-- Archivo creado  - sábado-noviembre-29-2014   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence DEMO_CUST_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "DEMO_CUST_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence DEMO_ORDER_ITEMS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "DEMO_ORDER_ITEMS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence DEMO_ORD_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "DEMO_ORD_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 11 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence DEMO_PROD_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "DEMO_PROD_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Sequence DEMO_USERS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "DEMO_USERS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE
/
--------------------------------------------------------
--  DDL for Table MEMBRESIA
--------------------------------------------------------

  CREATE TABLE "MEMBRESIA" ("CODIGO" NUMBER, "NOMBRE" VARCHAR2(20), "NFOTOS" NUMBER, "PRECIO" NUMBER, "NDESTACADOS" NUMBER, "NPUBLICACIONES" NUMBER)
/
REM INSERTING into MEMBRESIA
SET DEFINE OFF;
Insert into MEMBRESIA (CODIGO,NOMBRE,NFOTOS,PRECIO,NDESTACADOS,NPUBLICACIONES) values ('0','bronce','2','0','0','1');
Insert into MEMBRESIA (CODIGO,NOMBRE,NFOTOS,PRECIO,NDESTACADOS,NPUBLICACIONES) values ('1','plata','3','10000','1','2');
Insert into MEMBRESIA (CODIGO,NOMBRE,NFOTOS,PRECIO,NDESTACADOS,NPUBLICACIONES) values ('2','oro','5','18000','2','5');
--------------------------------------------------------
--  DDL for Index MEMBRESIA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MEMBRESIA_PK" ON "MEMBRESIA" ("CODIGO")
/
--------------------------------------------------------
--  Constraints for Table MEMBRESIA
--------------------------------------------------------

  ALTER TABLE "MEMBRESIA" ADD CONSTRAINT "MEMBRESIA_PK" PRIMARY KEY ("CODIGO") ENABLE
  ALTER TABLE "MEMBRESIA" MODIFY ("CODIGO" NOT NULL ENABLE)
/
--------------------------------------------------------
--  DDL for Function CUSTOM_AUTH
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "CUSTOM_AUTH" (p_username in VARCHAR2, p_password in VARCHAR2)
return BOOLEAN
is
  l_password varchar2(4000);
  l_stored_password varchar2(4000);
  l_expires_on date;
  l_count number;
begin
-- First, check to see if the user is in the user table
select count(*) into l_count from demo_users where user_name = p_username;
if l_count > 0 then
  -- First, we fetch the stored hashed password & expire date
  select password, expires_on into l_stored_password, l_expires_on
   from demo_users where user_name = p_username;

  -- Next, we check to see if the user's account is expired
  -- If it is, return FALSE
  if l_expires_on > sysdate or l_expires_on is null then

    -- If the account is not expired, we have to apply the custom hash
    -- function to the password
    l_password := custom_hash(p_username, p_password);

    -- Finally, we compare them to see if they are the same and return
    -- either TRUE or FALSE
    if l_password = l_stored_password then
      return true;
    else
      return false;
    end if;
  else
    return false;
  end if;
else
  -- The username provided is not in the DEMO_USERS table
  return false;
end if;
end;
/
--------------------------------------------------------
--  DDL for Function CUSTOM_HASH
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "CUSTOM_HASH" (p_username in varchar2, p_password in varchar2)
return varchar2
is
  l_password varchar2(4000);
  l_salt varchar2(4000) := 'P6YOSQFY5OGL7OTW6AOHTEHI18LRVE';
begin

-- This function should be wrapped, as the hash algorhythm is exposed here.
-- You can change the value of l_salt or the method of which to call the
-- DBMS_OBFUSCATOIN toolkit, but you much reset all of your passwords
-- if you choose to do this.

l_password := utl_raw.cast_to_raw(dbms_obfuscation_toolkit.md5
  (input_string => p_password || substr(l_salt,10,13) || p_username ||
    substr(l_salt, 4,10)));
return l_password;
end;
/
