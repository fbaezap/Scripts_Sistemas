--Borrar Tablas
begin

  FOR fila IN (select 'DROP TRIGGER '||trigger_name sentencia from user_triggers) 
  LOOP
    EXECUTE IMMEDIATE fila.sentencia;
  END LOOP;

  FOR fila IN (select 'DROP SEQUENCE '||sequence_name sentencia from user_sequences) 
  LOOP
    EXECUTE IMMEDIATE fila.sentencia;
  END LOOP;

  FOR fila1 IN (select 'drop table '||table_name||' cascade constraints' sentencia from user_tables) 
  LOOP
    EXECUTE IMMEDIATE fila1.sentencia;
  END LOOP;
end;

