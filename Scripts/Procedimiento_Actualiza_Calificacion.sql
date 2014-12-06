CREATE OR REPLACE PROCEDURE actualizar_calificacionv(rut2 in varchar2) as
 promedio float;
begin
  select coalesce(avg(cal.calificacion),0) into promedio from calificacionv cal where cal.vendedor_rut=rut2;
  UPDATE vendedor SET calificacion=promedio where rut = rut2;
end;