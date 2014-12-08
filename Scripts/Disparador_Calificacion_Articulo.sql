CREATE OR REPLACE TRIGGER cal_articulo_TRG 
AFTER INSERT OR UPDATE
ON calificacion 
DECLARE
  cod_n articulo.codigo%type;
  promedio FLOAT;
  cursor c1 is select codigo from articulo;
BEGIN
  open c1;
  loop
    fetch c1 into cod_n;
    exit when c1%NOTFOUND;
    actualizar_calificacion(cod_n);
  end loop;
  close c1;
END;
