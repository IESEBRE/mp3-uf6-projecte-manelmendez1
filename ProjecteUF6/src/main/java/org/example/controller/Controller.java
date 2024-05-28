package org.example.controller;

import org.example.model.entities.Psicotropic;
import org.example.model.exceptions.DAOException;
import org.example.model.impls.PsicotropicDAOJDBCOracleImpl;
import org.example.view.FinestraAnaleg;
import org.example.view.FinestraPsicotropic;
import org.example.view.Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.regex.Pattern;

public class Controller implements PropertyChangeListener {

    private Model model = new Model();
    private PsicotropicDAOJDBCOracleImpl dadesPsicotropics;
    private FinestraPsicotropic view1;

    public static final String PROP_EXCEPCIO = "excepcio";
    private DAOException excepcio;

    public DAOException getExcepcio() {
        return excepcio;
    }

    public void setExcepcio(DAOException excepcio) {
        DAOException oldExcepcio = this.excepcio;
        this.excepcio = excepcio;
        canvis.firePropertyChange(PROP_EXCEPCIO, oldExcepcio, excepcio);
    }

    PropertyChangeSupport canvis = new PropertyChangeSupport(this);


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        DAOException excepcio = (DAOException) evt.getNewValue();

        try {
            throw excepcio;

        } catch (DAOException e) {

            switch (evt.getPropertyName()) {

                case PROP_EXCEPCIO:

                    //switch (excepcio.getTipo()) {

                        //case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 903, 904, 936, 942, 925,1000, 1400, 1403, 1438, 1045,1722, 1747, 4091, 6502, 12154, 2292, 2291, 1017, 20001, 32795:
                    JOptionPane.showMessageDialog(view1, excepcio.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                    //}
            }
        }
    }

    public Controller(PsicotropicDAOJDBCOracleImpl dadesPsicotropics, FinestraPsicotropic view1) {
        this.dadesPsicotropics = dadesPsicotropics;
        this.view1 = view1;

        canvis.addPropertyChangeListener(this);

        try {
            //Executem els scripts de creació de la base de dades i de creació dels procediments emmagatzemats
            dadesPsicotropics.executeSqlScript("./src/main/resources/pl.sql");
            dadesPsicotropics.initDB();
            dadesPsicotropics.executeSqlScript("./src/main/resources/pl2.sql");

        } catch (DAOException e) {

            setExcepcio(new DAOException(e.getTipo()));

        } catch (Exception e) {
            setExcepcio(new DAOException(100));
        }


        lligaVistaModel();

        afegirListeners();

        view1.setVisible(true);

    }

    private void lligaVistaModel() {

        //Carreguem les dades de la base de dades a la taula

        try {

            List<Psicotropic> dadesBase = dadesPsicotropics.getAll();
            setModelPsicotropic(model.getModelPsicotropic(), dadesBase);
        } catch (DAOException e) {
            setExcepcio(new DAOException(e.getTipo()));

        } catch (Exception e) {
            setExcepcio(new DAOException(100));
        }

        JTable taula = view1.getTable1();
        taula.setModel(this.model.getModelPsicotropic());

        //Amaguem la columna de l'ID del psicotròpic
        taula.getColumnModel().getColumn(0).setMinWidth(0);
        taula.getColumnModel().getColumn(0).setMaxWidth(0);
        taula.getColumnModel().getColumn(0).setWidth(0);


    }

    /**
     * Mètode que carrega les dades de la base de dades a la taula
     * @param modelPsicotropic Model de la taula
     * @param dadesBase Llista de psicotròpics a carregar
     */

    private void setModelPsicotropic(DefaultTableModel modelPsicotropic, List<Psicotropic> dadesBase) {

        for (Psicotropic psicotropic : dadesBase) {
            modelPsicotropic.addRow(new Object[]{psicotropic.getId(), psicotropic.getNom(), psicotropic.getClassificacio(), psicotropic.getVidaMitja(), psicotropic.isLegal(), psicotropic.getTipus()});
        }
    }

    private void afegirListeners() {

        Model modelo = this.model;
        DefaultTableModel modelPsicotropic = modelo.getModelPsicotropic();
        DefaultTableModel modelAnaleg = modelo.getModelAnaleg();

        JTable taulaPsicotropics = view1.getTable1();

        JButton insertarButton = view1.getInsertarButton1();
        JButton modificarButton = view1.getModificarButton1();
        JButton borrarButton = view1.getBorrarButton1();
        JButton analegsButton = view1.getAnalegsButton1();

        JTextField nomField = view1.getNomField();
        JTextField classificacioField = view1.getClassificacioField();
        JTextField vidaMitjaField = view1.getVidaMitjaField();

        JCheckBox esLegalCheckBox = view1.getEsLegalCheckBox();

        JComboBox comboBoxTipus = view1.getComboBoxTipus();

        view1.getInsertarButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String psicotropicNatural = "^[A-Z][a-z]{2,19}$";

                //El nom del psicotròpic artificial ha de ser un nom que comenci per un número o lletra majúscula seguit d'un guió o d'una o més lletres majúscula

                String psicotropicArtificial = "^(?:[A-Z0-9]+-)?[A-Z][A-Za-z0-9]*(?:-[A-Za-z0-9]+)*$";

                //El nom del psicotròpic artificial pot ser també un nom que contingue comes, apostrofs, i números
                String artificial2 = "\\b(?:[A-Z0-9][\\w'-]*)(?:\\s*[,-]?\\s*[A-Z0-9][\\w'-]*)*\\b";


                Pattern pattern = Pattern.compile(psicotropicNatural);
                Pattern pattern2 = Pattern.compile(psicotropicArtificial);
                Pattern pattern3 = Pattern.compile(artificial2);


                try {

                    //Mirem si els camps estan buits

                    if (nomField.getText().isEmpty() || classificacioField.getText().isEmpty() || vidaMitjaField.getText().isEmpty()) {
                        throw new DAOException(5);
                    }

                    //Mirem si el nom del psicotròpic compleix amb el format
                    if (!pattern.matcher(nomField.getText()).matches() && !pattern2.matcher(nomField.getText()).matches() && !pattern3.matcher(nomField.getText()).matches()) {
                        throw new DAOException(15);
                    }

                    //Mirem que el nom del psicotròpic no esitgui repetit
                    for (int i = 0; i < modelPsicotropic.getRowCount(); i++) {
                        if (nomField.getText().equals(modelPsicotropic.getValueAt(i, 1))) {
                            throw new DAOException(8);
                        }
                    }


                    String nom = nomField.getText();
                    int classificacio = Integer.parseInt(classificacioField.getText());
                    double vidaMitja = Double.parseDouble(vidaMitjaField.getText());
                    boolean esLegal = esLegalCheckBox.isSelected();
                    String tipus = comboBoxTipus.getSelectedItem().toString();

                    Psicotropic psicotropic = new Psicotropic(nom, classificacio, vidaMitja, esLegal, tipus);


                    try {

                        //Insertem les dades a la base de dades i a la taula

                        dadesPsicotropics.save(psicotropic);

                        //Obtenim l'id del psicotròpic generat automàticament que acabem d'insertar
                        Long id = dadesPsicotropics.getIdPsicotropic(psicotropic);
                        modelPsicotropic.addRow(new Object[]{id, nom, classificacio, vidaMitja, esLegal, tipus});

                        nomField.setText("");
                        classificacioField.setText("");
                        vidaMitjaField.setText("");
                        esLegalCheckBox.setSelected(false);


                    } catch (DAOException ex) {
                        setExcepcio(new DAOException(ex.getTipo()));
                    } catch (Exception ex) {
                        setExcepcio(new DAOException(100));
                    }

                } catch (NumberFormatException ex) {

                    setExcepcio(new DAOException(3));

                } catch (DAOException ex) {

                    setExcepcio(new DAOException(ex.getTipo()));

                } catch (Exception ex) {
                    setExcepcio(new DAOException(100));

                }
            }
        });

        view1.getBorrarButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int filaSeleccionada = taulaPsicotropics.getSelectedRow();

                if (filaSeleccionada != -1) {
                    //Obtenim l'id del psicotròpic seleccionat
                    Long id = (Long) modelPsicotropic.getValueAt(filaSeleccionada, 0);
                    try {
                        //Borrem el psicotròpic seleccionat de la base de dades i de la taula
                        dadesPsicotropics.delete(id);
                        modelPsicotropic.removeRow(filaSeleccionada);

                        nomField.setText("");
                        classificacioField.setText("");
                        vidaMitjaField.setText("");
                        esLegalCheckBox.setSelected(false);


                    } catch (DAOException ex) {

                        setExcepcio(new DAOException(ex.getTipo()));

                    } catch (Exception ex) {
                        setExcepcio(new DAOException(100));
                    }

                } else {

                    JOptionPane.showMessageDialog(view1, "Selecciona una fila per borrar");
                }
            }
        });

        view1.getTable1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int filaSeleccionada = taulaPsicotropics.getSelectedRow();

                if (filaSeleccionada != -1) {

                    //Omplim els JTextField amb les dades del psicotròpic seleccionat

                    String nom = (String) modelPsicotropic.getValueAt(filaSeleccionada, 1);
                    int classificacio = (int) modelPsicotropic.getValueAt(filaSeleccionada, 2);
                    double vidaMitja = (double) modelPsicotropic.getValueAt(filaSeleccionada, 3);
                    boolean esLegal = (boolean) modelPsicotropic.getValueAt(filaSeleccionada, 4);
                    String tipus = (String) modelPsicotropic.getValueAt(filaSeleccionada, 5);

                    nomField.setText(nom);
                    classificacioField.setText(String.valueOf(classificacio));
                    vidaMitjaField.setText(String.valueOf(vidaMitja));
                    esLegalCheckBox.setSelected(esLegal);
                    comboBoxTipus.setSelectedItem(tipus);
                }
            }
        });

        view1.getModificarButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //El nom del psicotròpic ha de començar per una lletra majúscula o un número

                String psicotropicNatural = "^[A-Z][a-z]{2,19}$";

                //El nom del psicotròpic artificial ha de ser un nom que comenci per un número o lletra majúscula seguit d'un guió o d'una o més lletres majúscula

                String psicotropicArtificial = "^(?:[A-Z0-9]+-)?[A-Z][A-Za-z0-9]*(?:-[A-Za-z0-9]+)*$";

                //El nom del psicotròpic artificial pot ser també un nom que contingue comes, apostrofs, i números
                String artificial2 = "\\b(?:[A-Z0-9][\\w'-]*)(?:\\s*[,-]?\\s*[A-Z0-9][\\w'-]*)*\\b";

                Pattern pattern = Pattern.compile(psicotropicNatural);
                Pattern pattern2 = Pattern.compile(psicotropicArtificial);
                Pattern pattern3 = Pattern.compile(artificial2);



                int filaSeleccionada = taulaPsicotropics.getSelectedRow();

                if (filaSeleccionada != -1) {

                    try {

                        //Mirem si els camps estan buits

                        if (nomField.getText().isEmpty() || classificacioField.getText().isEmpty() || vidaMitjaField.getText().isEmpty()) {
                            throw new DAOException(5);
                        }

                        //Mirem si el nom del psicotròpic compleix amb el format

                        if (!pattern2.matcher(nomField.getText()).matches() && !pattern3.matcher(nomField.getText()).matches() && !pattern.matcher(nomField.getText()).matches()) {
                            throw new DAOException(15);
                        }

                        //Mirem que el nom del psicotròpic no esitgui repetit, exceptuant el que està seleccionat

                        for (int i = 0; i < modelPsicotropic.getRowCount(); i++) {
                            if (nomField.getText().equals(modelPsicotropic.getValueAt(i, 1)) && i != filaSeleccionada) {
                                throw new DAOException(8);
                            }
                        }

                        //Obtenim les dades dels JTextField i els posem a un objecte Psicotropic

                        Long id = (Long) modelPsicotropic.getValueAt(filaSeleccionada, 0);
                        String nom = nomField.getText();
                        int classificacio = Integer.parseInt(classificacioField.getText());
                        double vidaMitja = Double.parseDouble(vidaMitjaField.getText());
                        boolean esLegal = esLegalCheckBox.isSelected();
                        String tipus = comboBoxTipus.getSelectedItem().toString();

                        Psicotropic psicotropic = new Psicotropic(id, nom, classificacio, vidaMitja, esLegal, tipus);

                        try {

                            //Modifiquem les dades a la base de dades i a la taula
                            dadesPsicotropics.modify(psicotropic);
                            modelPsicotropic.setValueAt(nom, filaSeleccionada, 1);
                            modelPsicotropic.setValueAt(classificacio, filaSeleccionada, 2);
                            modelPsicotropic.setValueAt(vidaMitja, filaSeleccionada, 3);
                            modelPsicotropic.setValueAt(esLegal, filaSeleccionada, 4);
                            modelPsicotropic.setValueAt(tipus, filaSeleccionada, 5);
                        } catch (DAOException ex) {
                            setExcepcio(new DAOException(ex.getTipo()));
                        } catch (Exception ex) {
                            setExcepcio(new DAOException(100));
                        }

                    //Capturem l'excepció si els camps de classificació o vida mitja no són números

                    } catch (NumberFormatException ex) {

                        setExcepcio(new DAOException(3));

                    } catch (DAOException ex) {

                        setExcepcio(new DAOException(ex.getTipo()));

                    } catch (Exception ex) {
                        setExcepcio(new DAOException(100));
                    }

                } else {

                    JOptionPane.showMessageDialog(view1, "Selecciona una fila per modificar");
                }
            }
        });

        view1.getAnalegsButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (taulaPsicotropics.getSelectedRow() != -1) {

                    //Bloquejem la taula de psicotròpics
                    view1.setEnabled(false);

                    //Obtenim l'id del psicotropic seleccionat
                    Long idPsicotropic = (Long) modelPsicotropic.getValueAt(taulaPsicotropics.getSelectedRow(), 0);


                    //Creem una finestra d'anàlegs
                    new Controller2(new Model(), new FinestraAnaleg(), view1, dadesPsicotropics, idPsicotropic);

                } else {

                    JOptionPane.showMessageDialog(view1, "Selecciona un psicotròpic per veure els seus anàlegs");
                }

            }
        });
    }
}
