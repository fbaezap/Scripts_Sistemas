CREATE OR REPLACE PROCEDURE Display_Image(p_id NUMBER) IS
    Photo BLOB;
    v_amt NUMBER DEFAULT 4096;
    v_off NUMBER DEFAULT 1;
    v_raw RAW(4096);
  BEGIN

    -- Get the blob image
    SELECT image
    INTO   Photo
    FROM   PHOTOS
    WHERE  IMAGEID = p_id;

    owa_util.mime_header('images/jpg'); 
    BEGIN
      LOOP
        -- Read the BLOB
        dbms_lob.READ(Photo, v_amt, v_off, v_raw);
        -- Display image
        htp.prn(utl_raw.cast_to_varchar2(v_raw));
        v_off := v_off + v_amt;
        v_amt := 4096;
      END LOOP;
      dbms_lob.CLOSE(Photo);
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        NULL;
    END;

  END;