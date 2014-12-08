CREATE OR REPLACE TRIGGER calv_vendedor_TRG 
AFTER INSERT OR UPDATE
ON calificacionv 
DECLARE
  rut_n vendedor.rut%type;
  promedio FLOAT;
  cursor c1 is select rut from vendedor;
BEGIN
  open c1;
  loop
    fetch c1 into rut_n;
    exit when c1%NOTFOUND;
    actualizar_calificacionv(rut_n);
  end loop;
  close c1;
END;
