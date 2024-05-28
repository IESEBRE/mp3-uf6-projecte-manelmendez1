package org.example.model.daos;

import org.example.model.entities.Psicotropic;
import org.example.model.exceptions.DAOException;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public interface DAO <T> {

    void save(T t) throws DAOException;
    void modify(T t) throws DAOException;
    void delete(Long id) throws DAOException;
    T get(Long id) throws DAOException;
    List<T> getAll() throws DAOException;
    void initDB() throws DAOException;
    List<Psicotropic.Analeg> getAll(Long idPsicotropic) throws DAOException;
    void saveAnaleg(Psicotropic.Analeg analeg) throws DAOException;
    void deleteAnaleg(Long id) throws DAOException;
    Long getIdPsicotropic(Psicotropic psicotropic) throws DAOException;
    Long getIdAnaleg(Psicotropic.Analeg analeg) throws DAOException;
    void modifyAnaleg(Psicotropic.Analeg analeg) throws DAOException;
    void findString(String text, Long id, JFrame finestraAnaleg) throws DAOException;
    void executeSqlScript(String sqlFilePatch) throws DAOException;
}
