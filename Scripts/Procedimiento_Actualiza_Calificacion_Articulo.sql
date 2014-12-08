CREATE OR REPLACE PROCEDURE actualizar_calificacion(cod in NUMERIC) as
 promedio float;
begin
  select coalesce(avg(cal.calificacion),0) into promedio from calificacion cal where cal.articulo_codigo =cod;
  UPDATE articulo SET calificacion=promedio where codigo=cod;
end;