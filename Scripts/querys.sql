select art.vendedor_rut, art.codigo from articulo art group by art.vendedor_rut;

select ven.rut, mem.ndestacados,mem.nfotos,mem.npublicaciones 
from vendedor ven, membresia mem 
where ven.membresia_codigo=mem.codigo;

--Cuenta cuantos usuarios tiene que membresia
select mem.codigo,mem.nombre,mem.tipo , count(ven.rut) from vendedor ven, membresia mem where ven.membresia_codigo=mem.codigo group by (mem.codigo,mem.nombre,mem.tipo) order by mem.codigo asc;

--Calcular calificacion
select coalesce(avg(cal.calificacion),0) from calificacionv cal where cal.vendedor_rut='18409893-8';

select ven.rut,avg(coalesce(cal.calificacion,0)) from vendedor ven, calificacionv cal where ven.rut = cal.vendedor_rut(+) group by ven.rut;

select rut from vendedor;