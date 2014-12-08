CREATE OR REPLACE TRIGGER ordena_articulo_TRG 
AFTER INSERT or UPDATE or DELETE
ON Articulo
DECLARE
  cod_n articulo.codigo%type;
  pag Numeric;
  co NUMERIC;
  cursor c2 is select codigo from articulo order by calificacion desc;
BEGIN
  co:=1;
  pag:=1;
  open c2;
  loop
    fetch c2 into cod_n;
    exit when c2%NOTFOUND;
    update articulo set PaginaArticulo_pagina values pag where codigo=cod_n;
    co:=co+1;
    if co=6 then
      co:=1;
      pag:=pag+1;
    end if;
  end loop;
  close c2;
END;
