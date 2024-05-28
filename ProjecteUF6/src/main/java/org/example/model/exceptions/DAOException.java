package org.example.model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class DAOException extends Exception {

    private static final Map<Integer, String> missatges = new HashMap<>();

    static {
        missatges.put(0, "Error al connectar a la BD!!");
        missatges.put(1, "Restricció d'integritat violada - clau primària duplicada");
        missatges.put(2, "Error de SQL");
        missatges.put(3, "La classificació o la vida mitja no poden ser cadenes");
        missatges.put(904, "Nom de columna no vàlid");
        missatges.put(906, "Falta un parèntesi esquerre");
        missatges.put(907, "Falta un parèntesi dret");
        missatges.put(908, "Falta la paraula clau NULL");
        missatges.put(911, "Caràcter no vàlid");
        missatges.put(903, "Nom de taula no vàlid");
        missatges.put(925, "Falta la paraula clau INTO");
        missatges.put(905 ,"Falta una paraula clau en la sentència SQL");
        missatges.put(900, "Sentència SQL no vàlida");
        missatges.put(4, "Error de sintaxi");
        missatges.put(913, "Massa valors");
        missatges.put(936, "Falta expressió en l'ordre SQL");
        missatges.put(942, "La taula o la vista no existeix");
        missatges.put(947, "No hi ha prous valors");
        missatges.put(1045, "Permisos insuficients per a la connexió a la base de dades");
        missatges.put(1000, "S'ha superat el nombre màxim de cursors oberts");
        missatges.put(1400, "Inserció de valor nul en una columna que no permet nuls");
        missatges.put(1438, "No es permet un valor superior a la precisió especificada en la columna");
        missatges.put(1403, "No s'ha trobat cap dada");
        missatges.put(1722, "Ha fallat la conversió d'una cadena de caràcters a un número");
        missatges.put(1747, "El nombre de columnes de la vista no coincideix amb el nombre de columnes de les taules subjacents");
        missatges.put(4091, "Modificació d'un procediment o funció en execució actualment");
        missatges.put(6502, "Error numèric o de valor durant l'execució del programa");
        missatges.put(12154, "No s'ha pogut resoldre el nom del servei de la base de dades Oracle o l'identificador de connexió");
        missatges.put(2292, "S'ha violat la restricció d'integritat -  s'ha trobat un registre fill");
        missatges.put(1017, "Usuari no vàlid o contrasenya incorrecta");
        missatges.put(32795, "No es pot inserir en una columna d'identitat sempre generada");
        missatges.put(5, "Algun camp està buit");
        missatges.put(7, "La vida mitja ha de ser un número entre 0.1 i 200");
        missatges.put(8, "El nom ja existeix");
        missatges.put(9, "No s'ha trobat el fitxer Properties");
        missatges.put(10, "Error d'entrada/sortida");
        missatges.put(11, "El número de l'analeg no pot ser una cadena");
        missatges.put(12, "El número de l'anàleg no pot ser menor o igual a 0");
        missatges.put(13, "L'anàleg ja existeix");
        missatges.put(20001, "La classifiació ha de ser un número entre 1 i 5");
        missatges.put(20002, "La vida mitja ha de ser un número entre 0.1 i 200");
        missatges.put(6, "Violació de la restricció d'integritat");
        missatges.put(2291, "Violació de la restricció d'integritat - clau primària no trobada");
        missatges.put(2290, "Restricció de check violada");
        missatges.put(1031, "No teniu privilegis per executar procediments emmagatzemats");
        missatges.put(15, "El nom no compleix en el format correcte. El nom ha de: \n - Començar en una lletra majúscula o un número \n - Contindre guions, números, apòstrofs o altres lletres \n - No pot tenir altres caràcters que no siguin guions o apòstrofs \n - No poden acabar en guió o apòstrof");
        missatges.put(100, "Error desconegut");
        missatges.put(16, "No s'ha trobat l'script SQL");
    }

    private int tipo;

    public DAOException(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String getMessage() {
        return missatges.get(this.tipo);
    }

    public int getTipo() {
        return tipo;
    }
}
