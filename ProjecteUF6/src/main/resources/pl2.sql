
CREATE OR REPLACE TRIGGER CHECK_CLASSIFICACIO
BEFORE INSERT OR UPDATE OF classificacio ON psicotropics
FOR EACH ROW

DECLARE

    ex_classificacio_out_of_range EXCEPTION;

BEGIN

    IF :NEW.classificacio < 1 OR :NEW.classificacio > 5 THEN

        RAISE ex_classificacio_out_of_range;

    END IF;

EXCEPTION

    WHEN ex_classificacio_out_of_range THEN

        RAISE_APPLICATION_ERROR(-20001, 'El valor de la classificació ha de ser entre 1 i 5');

END;
/
CREATE OR REPLACE FUNCTION buscar_analegs(p_nom IN VARCHAR2, p_id_psicotropic IN NUMBER)
RETURN SYS_REFCURSOR IS

    c_analegs SYS_REFCURSOR;

BEGIN

    OPEN c_analegs FOR SELECT NUM_ANALEG, NOM FROM ANALEGS WHERE UPPER(NOM) LIKE UPPER('%' || p_nom || '%') AND ID_PSICOTROPIC = p_id_psicotropic ORDER BY ID;
    RETURN c_analegs;

END buscar_analegs;
/
CREATE OR REPLACE TRIGGER check_vidaMitja
BEFORE INSERT OR UPDATE OF vidaMitja ON psicotropics
FOR EACH ROW
DECLARE
  ex_vidaMitja_out_of_range EXCEPTION;
BEGIN
  IF :NEW.vidaMitja < 0.1 OR :NEW.vidaMitja > 200.0 THEN
    RAISE ex_vidaMitja_out_of_range;
  END IF;
EXCEPTION
  WHEN ex_vidaMitja_out_of_range THEN
    RAISE_APPLICATION_ERROR(-20002, 'El valor de vidaMitja ha de ser entre 0.1 y 200.0');
END;
/