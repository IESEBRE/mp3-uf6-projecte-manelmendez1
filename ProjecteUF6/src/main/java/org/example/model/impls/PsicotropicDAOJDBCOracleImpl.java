package org.example.model.impls;

import oracle.jdbc.OracleTypes;
import org.example.model.daos.DAO;
import org.example.model.entities.Psicotropic;
import org.example.model.exceptions.DAOException;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsicotropicDAOJDBCOracleImpl implements DAO<Psicotropic> {


    /**
     * Mètode per desar un psicotròpic a la base de dades
     * @param psicotropic objecte de la classe Psicotropic que volem desar a la base de dades
     * @throws DAOException es llança en cas que es produeixi un error de tipus SQL a la base de dades
     */

    @Override
    public void save(Psicotropic psicotropic) throws DAOException {


        Properties prop = new Properties();
        InputStream inputStream = null;

        //Insertem un psicotròpic a la base de dades amb JDBC
        try {

            inputStream = new FileInputStream("./src/main/resources/database.properties");
            prop.load(inputStream);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection conexio = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO PSICOTROPICS (NOM, CLASSIFICACIO, VIDAMITJA, LEGAL, CLASSEPSICOTROPIC) VALUES (?, ?, ?, ?, ?)";

            try {

                PreparedStatement sentencia = conexio.prepareStatement(sql);

                sentencia.setString(1, psicotropic.getNom());
                sentencia.setInt(2, psicotropic.getClassificacio());
                sentencia.setDouble(3, psicotropic.getVidaMitja());
                sentencia.setString(4, psicotropic.isLegal() ? "SÍ" : "NO");
                sentencia.setString(5, psicotropic.getTipus());

                sentencia.executeUpdate();

            } catch (SQLSyntaxErrorException ex) {

                switch (ex.getErrorCode()) {
                    case 904:
                        throw new DAOException(904);
                    case 942:
                        throw new DAOException(942);
                    case 936:
                        throw new DAOException(936);
                    case 947:
                        throw new DAOException(947);
                    case 925:
                        throw new DAOException(925);
                    case 903:
                        throw new DAOException(903);
                    default:
                        throw new DAOException(4);
                }

            } catch (SQLIntegrityConstraintViolationException ex) {

                switch (ex.getErrorCode()) {
                    case 2292:
                        throw new DAOException(2292);
                    case 1:
                        throw new DAOException(1);
                    default:
                        throw new DAOException(6);
                }

            } catch (SQLDataException ex) {

                switch (ex.getErrorCode()) {
                    case 1438:
                        throw new DAOException(1438);
                    case 1400:
                        throw new DAOException(1400);
                    default:
                        throw new DAOException(2);
                }

            } catch (SQLException ex) {

                switch (ex.getErrorCode()) {
                    case 32795:
                        throw new DAOException(32795);
                    case 20002:
                        throw new DAOException(20002);
                    case 20001:
                        throw new DAOException(20001);
                    default:
                        throw new DAOException(2);

                }

            } finally {

                if (conexio != null) {
                    conexio.close();
                }
            }

        } catch (SQLRecoverableException ex) {

                throw new DAOException(0);

        } catch (SQLException ex) {

            switch (ex.getErrorCode()) {
                case 1017:
                    throw new DAOException(1017);
                case 1045:
                    throw new DAOException(1045);
                default:
                    throw new DAOException(2);
            }
        } catch (FileNotFoundException ex) {

            throw new DAOException(9);

        } catch (IOException ex) {

            throw new DAOException(10);
        }
    }

    /**
     * Mètode per modificar un psicotròpic de la base de dades
     * @param psicotropic objecte de la classe Psicotropic que volem modificar a la base de dades
     * @throws DAOException es llança en cas que es produeixi un error de tipus SQL a la base de dades
     */

    @Override
    public void modify(Psicotropic psicotropic) throws DAOException {

        Properties prop = new Properties();
        InputStream inputStream;

        //Modifiquem un psicotròpic a la base de dades amb JDBC
        try {

            inputStream = new FileInputStream("./src/main/resources/database.properties");
            prop.load(inputStream);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");


            Connection conexio = DriverManager.getConnection(url, user, password);

            String sql = "UPDATE PSICOTROPICS SET NOM = ?, CLASSIFICACIO = ?, VIDAMITJA = ?, LEGAL = ?, CLASSEPSICOTROPIC = ? WHERE ID = ?";

            try {

                PreparedStatement sentencia = conexio.prepareStatement(sql);

                sentencia.setString(1, psicotropic.getNom());
                sentencia.setInt(2, psicotropic.getClassificacio());
                sentencia.setDouble(3, psicotropic.getVidaMitja());
                sentencia.setString(4, psicotropic.isLegal() ? "SÍ" : "NO");
                sentencia.setString(5, psicotropic.getTipus());
                sentencia.setLong(6, psicotropic.getId());

                sentencia.executeUpdate();

            } catch (SQLSyntaxErrorException ex) {

                switch (ex.getErrorCode()) {
                    case 904:
                        throw new DAOException(904);
                    case 905:
                        throw new DAOException(905);
                    case 942:
                        throw new DAOException(942);
                    case 936:
                        throw new DAOException(936);
                    case 947:
                        throw new DAOException(947);
                    case 925:
                        throw new DAOException(925);
                    case 903:
                        throw new DAOException(903);
                    case 900:
                        throw new DAOException(900);
                    case 906:
                        throw new DAOException(906);
                    case 907:
                        throw new DAOException(907);
                    default:
                        throw new DAOException(4);
                }

            } catch (SQLIntegrityConstraintViolationException ex) {

                switch (ex.getErrorCode()) {
                    case 2292:
                        throw new DAOException(2292);
                    case 2291:
                        throw new DAOException(2291);
                    case 2290:
                        throw new DAOException(2290);
                    case 1:
                        throw new DAOException(1);
                    default:
                        throw new DAOException(6);
                }

            } catch (SQLDataException ex) {

                switch (ex.getErrorCode()) {
                    case 1438:
                        throw new DAOException(1438);
                    case 1400:
                        throw new DAOException(1400);
                    default:
                        throw new DAOException(2);
                }

            } catch (SQLException ex) {

                switch (ex.getErrorCode()) {
                    case 32795:
                        throw new DAOException(32795);
                    case 20001:
                        throw new DAOException(20001);
                    case 20002:
                        throw new DAOException(20002);
                    default:
                        throw new DAOException(2);

                }

            } finally {

                if (conexio != null) {
                    conexio.close();
                }
            }

        } catch (SQLRecoverableException ex) {

                throw new DAOException(0);

        } catch (SQLException ex) {

            switch (ex.getErrorCode()) {
                case 1017:
                    throw new DAOException(1017);
                case 1045:
                    throw new DAOException(1045);
                default:
                    throw new DAOException(2);
            }

        } catch (FileNotFoundException ex) {

            throw new DAOException(9);

        } catch (IOException ex) {

            throw new DAOException(10);
        }



    }

    /**
     * Mètode per esborrar un psicotròpic de la base de dades
     * @param id identificador del psicotròpic que volem esborrar
     * @throws DAOException es llança en cas que es produeixi un error de tipus SQL a la base de dades
     */

    @Override
    public void delete(Long id) throws DAOException {

        Properties prop = new Properties();
        InputStream inputStream;

        //Esborrem un psicotròpic a la base de dades amb JDBC
        try {

            inputStream = new FileInputStream("./src/main/resources/database.properties");
            prop.load(inputStream);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection conexio = DriverManager.getConnection(url, user, password);

            String sql = "DELETE FROM PSICOTROPICS WHERE ID = ?";

            try {

                PreparedStatement sentencia = conexio.prepareStatement(sql);

                sentencia.setLong(1, id);

                sentencia.executeUpdate();

            } catch (SQLIntegrityConstraintViolationException ex) {

                switch (ex.getErrorCode()) {
                    case 2292:
                        throw new DAOException(2292);
                    case 1:
                        throw new DAOException(1);
                    default:
                        throw new DAOException(2);
                }

            } catch (SQLSyntaxErrorException ex) {

                switch (ex.getErrorCode()) {
                    case 904:
                        throw new DAOException(904);
                    case 942:
                        throw new DAOException(942);
                    case 936:
                        throw new DAOException(936);
                    default:
                        throw new DAOException(4);
                }

            } catch (SQLException ex) {

                throw new DAOException(2);

            } finally {

                if (conexio != null) {
                    conexio.close();
                }
            }

        } catch (SQLRecoverableException ex) {

            throw new DAOException(0);

        } catch (SQLException ex) {

            int errorCode = ex.getErrorCode();

            switch (errorCode) {
                case 1017:
                    throw new DAOException(1017);
                case 1045:
                    throw new DAOException(1045);
                default:
                    throw new DAOException(2);
            }
        } catch (FileNotFoundException ex) {

            throw new DAOException(9);

        } catch (IOException ex) {

            throw new DAOException(10);
        }
    }

    @Override
    public Psicotropic get(Long id) throws DAOException {

        //Llegim un psicotròpic a la base de dades amb JDBC
        return null;
    }

    /**
     * Mètode per obtenir tots els psicotròpics de la base de dades
     * @return un ArrayList amb tots els psicotròpics de la base de dades
     * @throws DAOException es llança en cas que es produeixi un error de tipus SQL a la base de dades
     */

    @Override
    public List<Psicotropic.Analeg> getAll(Long idPsicotropic) throws DAOException {

        List<Psicotropic.Analeg> analegs = new ArrayList<>();

        Properties prop = new Properties();
        InputStream inputStream;

        try {

            inputStream = new FileInputStream("./src/main/resources/database.properties");
            prop.load(inputStream);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection conexio = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM ANALEGS WHERE ID_PSICOTROPIC = ? ORDER BY ID";

            try {

                PreparedStatement sentencia = conexio.prepareStatement(sql);
                sentencia.setLong(1, idPsicotropic);

                ResultSet rs = sentencia.executeQuery();

                while (rs.next()) {


                    Long id = rs.getLong("ID");
                    int nom = rs.getInt("NUM_ANALEG");
                    String tipus = rs.getString("NOM");
                    Long idPsicotropic1 = rs.getLong("ID_PSICOTROPIC");

                    Psicotropic.Analeg analeg = new Psicotropic.Analeg(id, nom, tipus, idPsicotropic1);
                    analegs.add(analeg);

                }

            } catch (SQLSyntaxErrorException ex) {

                switch (ex.getErrorCode()) {
                    case 904:
                        throw new DAOException(904);
                    case 942:
                        throw new DAOException(942);
                    case 936:
                        throw new DAOException(936);
                    default:
                        throw new DAOException(4);
                }


            } catch (SQLException ex) {

                throw new DAOException(2);

            } finally {

                if (conexio != null) {
                    conexio.close();
                }

            }

        } catch (SQLRecoverableException ex) {

            throw new DAOException(0);


        } catch (SQLException ex) {


            switch (ex.getErrorCode()) {
                case 1017:
                    throw new DAOException(1017);
                case 1045:
                    throw new DAOException(1045);
                default:
                    throw new DAOException(2);
            }

        } catch (FileNotFoundException ex) {

            throw new DAOException(9);

        } catch (IOException ex) {

            throw new DAOException(10);
        }

        return analegs;
    }

    /**
     * Mètode per desar un anàleg a la base de dades
     * @param analeg objecte de la classe Analeg que volem desar a la base de dades
     * @throws DAOException es llança en cas que es produeixi un error de tipus SQL a la base de dades
     */

    @Override
    public void saveAnaleg(Psicotropic.Analeg analeg) throws DAOException {

        Properties prop = new Properties();
        InputStream inputStream = null;

        //Insertem un anàleg a la base de dades amb JDBC
        try {

            inputStream = new FileInputStream("./src/main/resources/database.properties");
            prop.load(inputStream);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection conexio = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO ANALEGS (NUM_ANALEG, NOM, ID_PSICOTROPIC) VALUES (?, ?, ?)";

            try {

                PreparedStatement sentencia = conexio.prepareStatement(sql);

                sentencia.setInt(1, analeg.getNumAnaleg());
                sentencia.setString(2, analeg.getNom());
                sentencia.setLong(3, analeg.getIdPsicotropic());

                sentencia.executeUpdate();

            } catch (SQLException ex) {

                throw new DAOException(2);

            } finally {

                if (conexio != null) {
                    conexio.close();
                }
            }

        } catch (SQLRecoverableException ex) {


            throw new DAOException(0);

        } catch (SQLException ex) {

            switch (ex.getErrorCode()) {
                case 1017:
                    throw new DAOException(1017);
                case 1045:
                    throw new DAOException(1045);
                default:
                    throw new DAOException(2);
            }
        } catch (FileNotFoundException ex) {

            throw new DAOException(9);

        } catch (IOException ex) {

            throw new DAOException(10);
        }
    }

    /**
     * Mètode per esborrar un anàleg de la base de dades
     * @param id identificador de l'anàleg que volem esborrar
     * @throws DAOException es llança en cas que es produeixi un error de tipus SQL a la base de dades
     */

    @Override
    public void deleteAnaleg(Long id) throws DAOException {

        Properties prop = new Properties();
        InputStream inputStream = null;

        //Esborrem un anàleg a la base de dades amb JDBC
        try {

            inputStream = new FileInputStream("./src/main/resources/database.properties");
            prop.load(inputStream);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection conexio = DriverManager.getConnection(url, user, password);

            String sql = "DELETE FROM ANALEGS WHERE ID = ?";

            try {

                PreparedStatement sentencia = conexio.prepareStatement(sql);

                sentencia.setLong(1, id);

                sentencia.executeUpdate();

            } catch (SQLException ex) {

                throw new DAOException(2);

            } finally {

                if (conexio != null) {
                    conexio.close();
                }
            }


        } catch (SQLRecoverableException ex) {

            throw new DAOException(0);


        } catch (SQLException ex) {

            switch (ex.getErrorCode()) {
                case 1017:
                    throw new DAOException(1017);
                case 1045:
                    throw new DAOException(1045);
                default:
                    throw new DAOException(2);
            }
        } catch (FileNotFoundException ex) {

            throw new DAOException(9);

        } catch (IOException ex) {

            throw new DAOException(10);
        }
    }

    /**
     * Mètode per obtenir l'ID d'un psicotròpic de la base de dades
     * @param psicotropic objecte de la classe Psicotropic del qual volem obtenir l'ID
     * @return l'ID del psicotròpic
     * @throws DAOException es llança en cas que es produeixi un error de tipus SQL a la base de dades
     */

    @Override
    public Long getIdPsicotropic(Psicotropic psicotropic) throws DAOException {

        Properties prop = new Properties();
        InputStream inputStream = null;

        try {

            inputStream = new FileInputStream("./src/main/resources/database.properties");
            prop.load(inputStream);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection conexio = DriverManager.getConnection(url, user, password);

            String sql = "SELECT ID FROM PSICOTROPICS WHERE NOM = ?";

            try {

                PreparedStatement sentencia = conexio.prepareStatement(sql);
                sentencia.setString(1, psicotropic.getNom());

                ResultSet rs = sentencia.executeQuery();

                if (rs.next()) {
                    return rs.getLong("ID");
                } else {
                    return null;
                }

            } catch (SQLException ex) {

                throw new DAOException(2);

            } finally {

                if (conexio != null) {
                    conexio.close();
                }
            }

        } catch (SQLRecoverableException ex) {

            throw new DAOException(0);


        } catch (SQLException ex) {

            switch (ex.getErrorCode()) {
                case 1017:
                    throw new DAOException(1017);
                case 1045:
                    throw new DAOException(1045);
                default:
                    throw new DAOException(2);
            }
        } catch (FileNotFoundException ex) {

            throw new DAOException(9);

        } catch (IOException ex) {

            throw new DAOException(10);
        }
    }

    /**
     * Mètode per obtenir l'ID d'un anàleg de la base de dades
     * @param analeg objecte de la classe Analeg del qual volem obtenir l'ID
     * @return l'ID de l'anàleg
     * @throws DAOException es llança en cas que es produeixi un error de tipus SQL a la base de dades
     */

    @Override
    public Long getIdAnaleg(Psicotropic.Analeg analeg) throws DAOException {

        Properties prop = new Properties();
        InputStream inputStream = null;

        try {

            inputStream = new FileInputStream("./src/main/resources/database.properties");
            prop.load(inputStream);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection conexio = DriverManager.getConnection(url, user, password);

            String sql = "SELECT ID FROM ANALEGS WHERE NUM_ANALEG = ? AND NOM = ? AND ID_PSICOTROPIC = ?";

            try {

                PreparedStatement sentencia = conexio.prepareStatement(sql);
                sentencia.setInt(1, analeg.getNumAnaleg());
                sentencia.setString(2, analeg.getNom());
                sentencia.setLong(3, analeg.getIdPsicotropic());

                ResultSet rs = sentencia.executeQuery();

                if (rs.next()) {
                    return rs.getLong("ID");
                } else {
                    return null;
                }

            } catch (SQLException ex) {

                throw new DAOException(2);

            } finally {

                if (conexio != null) {
                    conexio.close();
                }
            }

        } catch (SQLException ex) {

            switch (ex.getErrorCode()) {
                case 1017:
                    throw new DAOException(1017);
                case 1045:
                    throw new DAOException(1045);
                default:
                    throw new DAOException(2);
            }

        } catch (FileNotFoundException ex) {

            throw new DAOException(9);

        } catch (IOException ex) {

            throw new DAOException(10);
        }

    }

    /**
     * Mètode per modificar un anàleg de la base de dades
     * @param analeg objecte de la classe Analeg que volem modificar a la base de dades
     * @throws DAOException es llança en cas que es produeixi un error de tipus SQL a la base de dades
     */

    @Override
    public void modifyAnaleg(Psicotropic.Analeg analeg) throws DAOException {

        Properties prop = new Properties();
        InputStream inputStream = null;

        //Modifiquem un anàleg a la base de dades amb JDBC
        try {

            inputStream = new FileInputStream("./src/main/resources/database.properties");
            prop.load(inputStream);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection conexio = DriverManager.getConnection(url, user, password);

            String sql = "UPDATE ANALEGS SET NUM_ANALEG = ?, NOM = ? WHERE ID = ?";

            try {

                PreparedStatement sentencia = conexio.prepareStatement(sql);

                sentencia.setInt(1, analeg.getNumAnaleg());
                sentencia.setString(2, analeg.getNom());
                sentencia.setLong(3, analeg.getId());

                sentencia.executeUpdate();

            } catch (SQLException ex) {

                throw new DAOException(2);

            } finally {

                if (conexio != null) {
                    conexio.close();
                }
            }

        } catch (SQLException ex) {

            throw new DAOException(ex.getErrorCode());

        } catch (FileNotFoundException ex) {

            throw new DAOException(9);

        } catch (IOException ex) {

            throw new DAOException(10);
        }

    }

    /**
     * Mètode per cercar una cadena de text d'un nom d'anàleg a la base de dades
     * @param text cadena de text que volem cercar
     * @param id identificador del psicotròpic al que pertany l'anàleg
     * @param finestraAnaleg finestra on es mostrarà el resultat de la cerca
     * @throws DAOException es llança en cas que es produeixi un error de tipus SQL a la base de dades
     */

    @Override
    public void findString(String text, Long id, JFrame finestraAnaleg) throws DAOException {

        //Cercar una cadena de text d'un nom d'anàleg a la base de dades amb JDBC

        Properties prop = new Properties();
        InputStream inputStream;

        CallableStatement callableStatement = null;
        ResultSet cursor = null;

        try {

            inputStream = new FileInputStream("./src/main/resources/database.properties");
            prop.load(inputStream);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection conexio = DriverManager.getConnection(url, user, password);

            String sql = "{? = call BUSCAR_ANALEGS(?, ?)}";
            callableStatement = conexio.prepareCall(sql);

            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);

            callableStatement.setString(2, text);
            callableStatement.setLong(3, id);

            callableStatement.execute();

            cursor = (ResultSet) callableStatement.getObject(1);

            if (!cursor.next()) {

                JOptionPane.showMessageDialog(finestraAnaleg, "No s'ha trobat cap resultat", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                do {
                    int numAnaleg = cursor.getInt("NUM_ANALEG");
                    String nom = cursor.getString("NOM");

                    JOptionPane.showMessageDialog(finestraAnaleg, "Anàleg trobat: " + numAnaleg + " - " + nom);
                } while (cursor.next());
            }


        } catch (SQLException ex) {

            throw new DAOException(2);

        } catch (FileNotFoundException ex) {

            throw new DAOException(9);

        } catch (IOException ex) {

            throw new DAOException(10);
        } finally {

            try {

                if (cursor != null) {
                    cursor.close();
                }

                if (callableStatement != null) {
                    callableStatement.close();
                }

            } catch (SQLException ex) {

                throw new DAOException(2);
            }
        }

    }

    @Override
    public void executeSqlScript(String sqlFilePatch) throws DAOException {

        Properties prop = new Properties();
        InputStream inputStream;


        try {

            inputStream = new FileInputStream("./src/main/resources/database.properties");
            prop.load(inputStream);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection conn = DriverManager.getConnection(url, user, password);


            BufferedReader reader = new BufferedReader(new FileReader(sqlFilePatch));

            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");

            }

            reader.close();

            String[] inst = sb.toString().split("/");

            Statement st = conn.createStatement();

            for (int i = 0; i < inst.length; i++) {
                if (!inst[i].trim().equals("")) {
                    st.execute(inst[i]);
                }
            }

        } catch (FileNotFoundException ex) {

            throw new DAOException(16);
        } catch (IOException ex) {

            throw new DAOException(10);

        } catch (SQLSyntaxErrorException ex) {

            switch (ex.getErrorCode()) {
                case 904:
                    throw new DAOException(904);
                case 942:
                    throw new DAOException(942);
                case 936:
                    throw new DAOException(936);
                case 1031:
                    throw new DAOException(1031);
                default:
                    throw new DAOException(4);
            }

        } catch (SQLRecoverableException ex) {

            throw new DAOException(0);

        } catch (SQLException ex) {

            switch (ex.getErrorCode()) {
                case 1017:
                    throw new DAOException(1017);
                case 1045:
                    throw new DAOException(1045);
                default:
                    throw new DAOException(2);
            }

        }

    }

    /**
     * Mètode per obtenir tots els psicotròpics de la base de dades
     * @return un ArrayList amb tots els psicotròpics de la base de dades
     * @throws DAOException es llança en cas que es produeixi un error de tipus SQL a la base de dades
     */

    @Override
    public List<Psicotropic> getAll() throws DAOException {

        List<Psicotropic> psicotropics = new ArrayList<>();

        Properties prop = new Properties();
        InputStream inputStream = null;

        try {

            inputStream = new FileInputStream("./src/main/resources/database.properties");
            prop.load(inputStream);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection conexio = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM PSICOTROPICS ORDER BY ID";

            try {


                PreparedStatement sentencia = conexio.prepareStatement(sql);
                ResultSet rs = sentencia.executeQuery();

                while (rs.next()) {


                    Long id = rs.getLong("ID");
                    String nom = rs.getString("NOM");
                    int classificacio = rs.getInt("CLASSIFICACIO");
                    double vidaMitja = rs.getDouble("VIDAMITJA");
                    boolean legal = rs.getString("LEGAL").equals("SÍ");
                    String tipus = rs.getString("CLASSEPSICOTROPIC");

                    Psicotropic psicotropic = new Psicotropic(id, nom, classificacio, vidaMitja, legal, tipus);
                    psicotropics.add(psicotropic);
                }

            } catch (SQLSyntaxErrorException ex) {

                switch (ex.getErrorCode()) {
                    case 904:
                        throw new DAOException(904);
                    case 942:
                        throw new DAOException(942);
                    case 936:
                        throw new DAOException(936);
                    default:
                        throw new DAOException(4);
                }

            } catch (SQLException ex) {

                throw new DAOException(2);

            } finally {

                if (conexio != null) {
                    conexio.close();
                }
            }

        } catch (SQLRecoverableException ex) {

            throw new DAOException(0);


        } catch (SQLException ex) {

            switch (ex.getErrorCode()) {
                case 1017:
                    throw new DAOException(1017);
                case 1045:
                    throw new DAOException(1045);
                default:
                    throw new DAOException(2);
            }

        } catch (FileNotFoundException ex) {

            throw new DAOException(9);

        } catch (IOException ex) {

            throw new DAOException(10);
        }

        return psicotropics;

    }

    /**
     * Mètode per inicialitzar la base de dades
     * @throws DAOException es llança en cas que es produeixi un error de tipus SQL a la base de dades
     */

    @Override
    public void initDB() throws DAOException {

        Properties prop = new Properties();
        InputStream inputStream;

        try {

            inputStream = new FileInputStream("./src/main/resources/database.properties");
            prop.load(inputStream);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection conexio = DriverManager.getConnection(url, user, password);

            //Comprovem si la taula PSICOTROPICS i la taula ANALEGS existeixen
            PreparedStatement existenciaTaulaPsicotropics = conexio.prepareStatement("SELECT COUNT(*) FROM user_tables WHERE table_name = 'PSICOTROPICS'");
            ResultSet rsPsicotropic = existenciaTaulaPsicotropics.executeQuery();

            //Comprovem si la taula ANALEGS existeix
            PreparedStatement existenciaTaulaAnalegs = conexio.prepareStatement("SELECT COUNT(*) FROM user_tables WHERE table_name = 'ANALEGS'");
            ResultSet rsAnaleg = existenciaTaulaAnalegs.executeQuery();

            //Si no existeixen les creem, primer la taula PSICOTROPICS perquè la taula ANALEGS depèn d'ella (relació d'una a molts)
            if (!(rsPsicotropic.next() && rsPsicotropic.getInt(1) > 0)) {
                //Cridem a un procediment emmagatzemat que crea la taula PSICOTROPICS
                CallableStatement crearPsicotropics = conexio.prepareCall("{CALL CREAR_TAULA_PSICOTROPICS}");
                crearPsicotropics.execute();
            }

            //Si no existeix la taula ANALEGS la creem
            if (!(rsAnaleg.next() && rsAnaleg.getInt(1) > 0)) {
                //Cridem a un procediment emmagatzemat que crea la taula ANALEGS
                CallableStatement crearAnalegs = conexio.prepareCall("{CALL CREAR_TAULA_ANALEGS}");
                crearAnalegs.execute();
            }

        } catch (SQLRecoverableException ex) {

            throw new DAOException(0);

        } catch (SQLException ex) {

            throw new DAOException(1);

        } catch (FileNotFoundException ex) {

            throw new DAOException(9);

        } catch (IOException ex) {

            throw new DAOException(10);
        }
    }
}
