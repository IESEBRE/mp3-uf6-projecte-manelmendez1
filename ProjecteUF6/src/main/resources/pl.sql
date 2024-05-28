CREATE OR REPLACE PROCEDURE CREAR_TAULA_PSICOTROPICS IS
BEGIN

    EXECUTE IMMEDIATE 'CREATE TABLE PSICOTROPICS(

                          ID NUMBER GENERATED ALWAYS AS IDENTITY CONSTRAINT PS_ID_PK PRIMARY KEY,
                          NOM VARCHAR2(100),
                          CLASSIFICACIO NUMBER,
                          VIDAMITJA NUMBER,
                          LEGAL VARCHAR2(3),
                          CLASSEPSICOTROPIC VARCHAR2(50)
                      )';

    DBMS_OUTPUT.PUT_LINE('La taula PSICOTROPICS ha sigut creada correctament.');


EXCEPTION

    WHEN OTHERS THEN

        DBMS_OUTPUT.PUT_LINE('Error al crear la taula PSICOTROPICS ' || SQLERRM);

END;
/
CREATE OR REPLACE PROCEDURE CREAR_TAULA_ANALEGS IS
BEGIN



    EXECUTE IMMEDIATE 'CREATE TABLE ANALEGS(

                          ID NUMBER GENERATED ALWAYS AS IDENTITY CONSTRAINT ANALEG_ID_PK PRIMARY KEY,
                          NUM_ANALEG NUMBER,
                          NOM VARCHAR2(100),
                          ID_PSICOTROPIC NUMBER CONSTRAINT ANALEG_ID_PS_FK REFERENCES PSICOTROPICS(ID) ON DELETE CASCADE
                      )';

EXCEPTION

    WHEN OTHERS THEN

        DBMS_OUTPUT.PUT_LINE('Error al crear la taula ANALEGS ' || SQLERRM);

END;
/



  

